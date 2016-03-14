package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.DessertBean;
import edu.nju.desserthouse.action.business.OrderBean;
import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.action.business.WaiterBean;
import edu.nju.desserthouse.model.Dessert;
import edu.nju.desserthouse.model.Order;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.model.Waiter;
import edu.nju.desserthouse.service.DessertManageService;
import edu.nju.desserthouse.service.PlanManageService;
import edu.nju.desserthouse.service.UserManageService;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/Sell")
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	DessertManageService dessertService;
	@EJB
	PlanManageService planService;
	@EJB
	UserManageService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("sell") == null) {
			HttpSession session = null;
			session = request.getSession(false);
			OrderBean orderbean = new OrderBean();
			WaiterBean waiterbean = new WaiterBean();
			PlanBean planbean = new PlanBean();
			UserBean userbean = new UserBean();

			waiterbean = (WaiterBean) session.getAttribute("waiter_waiterbean");
			Waiter waiter = waiterbean.getWaiter();

			String day = "";
			if (request.getParameter("day") == null) {
				Order order = new Order();
				order.setContent("");
				order.setPrice(0.0);
				orderbean.setOrder(order);
				session.setAttribute("waiter_orderbean", orderbean);
				day = "Monday";
			} else {
				day = (String) request.getParameter("day");
			}
			List planlist = planService.findByHouseIdAndDay(waiter.getHouseid(), day);
			List passlist = new ArrayList();
			for (int i = 0; i < planlist.size(); i++) {
				Plan plan = (Plan) planlist.get(i);
				if (plan.getStatus().equals("已通过")) {
					passlist.add(plan);
				}
			}
			planbean.setPlanlist(passlist);
			session.setAttribute("waiter_planbean", planbean);

			List listuser = userService.findUserList();
			List reallylist = new ArrayList();
			for (int i = 0; i < listuser.size(); i++) {
				User user = (User) listuser.get(i);
				if (user.getStatus().equals("已激活")) {
					reallylist.add(user);
				}
			}
			userbean.setListuser(reallylist);
			session.setAttribute("waiter_userlist", userbean);

			response.sendRedirect("/DessertHouseWeb/waiter/sell.jsp");
		} else {
			String type = (String) request.getParameter("sell");
			switch (type) {
			case "add":
				addDessert(request, response);
				break;
			case "reduce":
				reduceDessert(request, response);
				break;
			}
		}

	}

	private void reduceDessert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		OrderBean orderbean = new OrderBean();

		int dessertid = Integer.parseInt(request.getParameter("dessertId"));
		orderbean = (OrderBean) session.getAttribute("waiter_orderbean");
		Order order = orderbean.getOrder();

		String newContent = "";
		Double totalPrice = 0.0;

		Dessert dessert = dessertService.findById(dessertid);
		String dessertname = dessert.getDessertname();

		String content = order.getContent();
		String[] contentlist = content.split(",");
		int num;
		boolean befound = false;
		boolean jude = false;
		int target = -1;
		for (int i = 0; i < contentlist.length; i++) {
			String[] list = contentlist[i].split(" X");
			if (list.length == 1) {
				jude = true;
				break;
			}
			String name = list[0];
			num = Integer.parseInt(list[1]);
			if (dessertname.equals(name)) {
				befound = true;
				num--;
				if (num == 0) {
					target = i;
				} else {
					contentlist[i] = name + " X" + num;
				}
			}
			Dessert d = dessertService.findByName(name);
			Double eachPrice = d.getPrice();
			totalPrice = totalPrice + eachPrice * num;
		}

		if (befound) {
			for (int i = 0; i < contentlist.length; i++) {
				boolean flag = true;
				if (i == target) {
					flag = false;
				}
				if (flag) {
					if (i == contentlist.length - 1) {
						newContent = newContent + contentlist[i];
					} else if ((i == contentlist.length - 2) && (target == contentlist.length - 1)) {
						newContent = newContent + contentlist[i];
					} else {
						newContent = newContent + contentlist[i] + ",";
					}
				}
			}
		} else {
			if (jude) {
				newContent = "";
				totalPrice = 0.0;
			} else {
				for (int i = 0; i < contentlist.length; i++) {
					if (i == contentlist.length - 1) {
						newContent = newContent + contentlist[i];
					} else {
						newContent = newContent + contentlist[i] + ",";
					}
				}
			}
		}

		order.setPrice(totalPrice);
		order.setContent(newContent);

		orderbean.setOrder(order);
		session.setAttribute("waiter_orderbean", orderbean);

		response.sendRedirect("/DessertHouseWeb/waiter/sell.jsp");
	}

	private void addDessert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		OrderBean orderbean = new OrderBean();

		int dessertid = Integer.parseInt(request.getParameter("dessertId"));
		orderbean = (OrderBean) session.getAttribute("waiter_orderbean");
		Order order = orderbean.getOrder();

		String newContent = "";
		Double totalPrice = 0.0;

		Dessert dessert = dessertService.findById(dessertid);
		String dessertname = dessert.getDessertname();

		String content = order.getContent();
		String[] contentlist = content.split(",");
		int num;
		boolean befound = false;
		boolean jude = false;
		for (int i = 0; i < contentlist.length; i++) {
			String[] list = contentlist[i].split(" X");
			if (list.length == 1) {
				jude = true;
				break;
			}
			String name = list[0];
			num = Integer.parseInt(list[1]);
			if (dessertname.equals(name)) {
				befound = true;
				num++;
				contentlist[i] = name + " X" + num;
			}
			Dessert d = dessertService.findByName(name);
			Double eachPrice = d.getPrice();
			totalPrice = totalPrice + eachPrice * num;
		}

		if (befound) {
			for (int i = 0; i < contentlist.length; i++) {
				if (i == contentlist.length - 1) {
					newContent = newContent + contentlist[i];
				} else {
					newContent = newContent + contentlist[i] + ",";
				}
			}
		} else {
			if (jude) {
				newContent = content + dessertname + " X" + 1;
				totalPrice = totalPrice + dessert.getPrice() * 1;
			} else {
				newContent = content + "," + dessertname + " X" + 1;
				totalPrice = totalPrice + dessert.getPrice() * 1;
			}
		}

		order.setPrice(totalPrice);
		order.setContent(newContent);

		orderbean.setOrder(order);
		session.setAttribute("waiter_orderbean", orderbean);

		response.sendRedirect("/DessertHouseWeb/waiter/sell.jsp");
	}

}
