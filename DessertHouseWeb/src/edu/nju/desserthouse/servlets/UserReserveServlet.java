package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.HouseBean;
import edu.nju.desserthouse.action.business.OrderBean;
import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.model.Account;
import edu.nju.desserthouse.model.Dessert;
import edu.nju.desserthouse.model.House;
import edu.nju.desserthouse.model.Order;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.DessertManageService;
import edu.nju.desserthouse.service.HouseManageService;
import edu.nju.desserthouse.service.OrderManageService;
import edu.nju.desserthouse.service.PlanManageService;
import edu.nju.desserthouse.service.UserManageService;

/**
 * Servlet implementation class UserReserveServlet
 */
@WebServlet("/Reserve")
public class UserReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	OrderManageService orderService;
	@EJB
	PlanManageService planService;
	@EJB
	DessertManageService dessertService;
	@EJB
	UserManageService userService;
	@EJB
	HouseManageService houseService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserReserveServlet() {
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

		if (request.getParameter("reserve") == null) {
			HttpSession session = null;
			session = request.getSession(false);
			OrderBean orderbean = new OrderBean();
			PlanBean planbean = new PlanBean();
			HouseBean housebean = new HouseBean();

			String day = "";
			List planlist;
			if (request.getParameter("day") == null) {
				session.setAttribute("user_houseid", -1);
				day = "Monday";
				planlist = planService.findByDay(day);

				Order order = new Order();
				order.setContent("");
				order.setPrice(0.0);
				orderbean.setOrder(order);
				session.setAttribute("user_orderbean", orderbean);
			} else {
				day = (String) request.getParameter("day");
				int houseid = (int) session.getAttribute("user_houseid");
				planlist = planService.findByHouseIdAndDay(houseid, day);
			}
			session.setAttribute("day", day);
			List reallylist = new ArrayList();
			for (int i = 0; i < planlist.size(); i++) {
				Plan p = (Plan) planlist.get(i);
				if (p.getStatus().equals("已通过")) {
					reallylist.add(p);
				}
			}
			planbean.setPlanlist(reallylist);
			session.setAttribute("user_planbean", planbean);

			List houselist = houseService.findHouseList();
			housebean.setHouselist(houselist);
			session.setAttribute("user_housebean", housebean);

			orderbean = (OrderBean) session.getAttribute("user_orderbean");
			session.setAttribute("user_orderbean", orderbean);

			int houseid = (int) session.getAttribute("user_houseid");
			response.sendRedirect("/DessertHouseWeb/user/reserve.jsp?house=" + houseid);

		} else {
			String type = (String) request.getParameter("reserve");
			switch (type) {
			case "addDessert":
				addDessert(request, response);
				break;
			case "reduceDessert":
				reduceDessert(request, response);
				break;
			case "addOrder":
				addOrder(request, response);
				break;
			}
		}

	}

	private void reduceDessert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		OrderBean orderbean = new OrderBean();

		int houseid = Integer.parseInt(request.getParameter("houseId"));
		House house = houseService.findHouseById(houseid);
		String housename = house.getHousename();

		int dessertid = Integer.parseInt(request.getParameter("dessertId"));
		orderbean = (OrderBean) session.getAttribute("user_orderbean");
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
			String[] array = list[0].split(":");
			String house_name = array[0];
			String name = array[1];
			num = Integer.parseInt(list[1]);
			if (dessertname.equals(name) && housename.equals(house_name)) {
				befound = true;
				num--;
				if (num == 0) {
					target = i;
				} else {
					contentlist[i] = house_name + ":" + name + " X" + num;
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
		session.setAttribute("user_orderbean", orderbean);

		int house_id = (int) session.getAttribute("user_houseid");
		response.sendRedirect("/DessertHouseWeb/user/reserve.jsp?house=" + house_id);
	}

	private void addDessert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		OrderBean orderbean = new OrderBean();

		int houseid = Integer.parseInt(request.getParameter("houseId"));
		House house = houseService.findHouseById(houseid);
		String housename = house.getHousename();

		int dessertid = Integer.parseInt(request.getParameter("dessertId"));
		orderbean = (OrderBean) session.getAttribute("user_orderbean");
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
			String[] array = list[0].split(":");
			String house_name = array[0];
			String name = array[1];
			num = Integer.parseInt(list[1]);
			if (dessertname.equals(name) && housename.equals(house_name)) {
				befound = true;
				num++;
				contentlist[i] = house_name + ":" + name + " X" + num;
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
				newContent = content + housename + ":" + dessertname + " X" + 1;
				totalPrice = totalPrice + dessert.getPrice() * 1;
			} else {
				newContent = content + "," + housename + ":" + dessertname + " X" + 1;
				totalPrice = totalPrice + dessert.getPrice() * 1;
			}
		}

		order.setPrice(totalPrice);
		order.setContent(newContent);

		orderbean.setOrder(order);
		session.setAttribute("user_orderbean", orderbean);
		
		int house_id = (int) session.getAttribute("user_houseid");
		response.sendRedirect("/DessertHouseWeb/user/reserve.jsp?house=" + house_id);
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		UserBean userbean = new UserBean();
		OrderBean orderbean = new OrderBean();

		int userid = Integer.parseInt(request.getParameter("userid"));
		String content = (String) request.getParameter("content");
		Double totalprice = Double.parseDouble(request.getParameter("totalprice"));
		String status = "未支付";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());

		String[] contentlist = content.split(",");
		for (int i = 0; i < contentlist.length; i++) {
			String[] list = contentlist[i].split(" X");
			String[] array = list[0].split(":");
			String house_name = array[0];
			String name = array[1];
			int num = Integer.parseInt(list[1]);

			String newcontent = name + " X" + num;
			House house = houseService.findHouseByName(house_name);

			Dessert d = dessertService.findByName(name);
			Double eachPrice = d.getPrice();

			Double realprice = eachPrice * num;

			Order order = new Order();
			order.setUserid(userid);
			User user_1 = userService.findById(userid);
			order.setUsername(user_1.getUsername());
			order.setHouseid(house.getHouseid());
			order.setHousename(house.getHousename());
			order.setContent(newcontent);
			order.setPrice(realprice);
			order.setStatus(status);
			order.setOrdertime(date);

			orderService.save(order);
		}

		List orderlist = orderService.findOrderByUserId(userid);
		orderbean.setOrderlist(orderlist);
		session.setAttribute("user_orderbean", orderbean);

		response.sendRedirect("/DessertHouseWeb/user/record.jsp");
	}

}
