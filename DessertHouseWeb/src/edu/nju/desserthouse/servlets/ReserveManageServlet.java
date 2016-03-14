package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.HouseBean;
import edu.nju.desserthouse.action.business.OrderBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.action.business.WaiterBean;
import edu.nju.desserthouse.model.Account;
import edu.nju.desserthouse.model.House;
import edu.nju.desserthouse.model.Order;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.model.Waiter;
import edu.nju.desserthouse.service.AccountManageService;
import edu.nju.desserthouse.service.OrderManageService;
import edu.nju.desserthouse.service.PlanManageService;
import edu.nju.desserthouse.service.UserManageService;

/**
 * Servlet implementation class ReserveManageServlet
 */
@WebServlet("/ReserveManage")
public class ReserveManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	AccountManageService accountService;
	@EJB
	UserManageService userService;
	@EJB
	OrderManageService orderService;
	@EJB
	PlanManageService planService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveManageServlet() {
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
		
		HttpSession session = null;
		session = request.getSession(false);
		UserBean userbean = new UserBean();
		OrderBean orderbean = new OrderBean();
		HouseBean housebean = new HouseBean();
		WaiterBean waiterbean = new WaiterBean();

		if (request.getParameter("orderid") != null) {
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			Order order = orderService.findOrderById(orderid);
			int userid = order.getUserid();
			User user = userService.findById(userid);

			Account account = accountService.find(userid);
			boolean result_1 = false;
			Double totalprice = order.getPrice();

			if (account.getPayment() <= 0) {
				response.sendRedirect("/DessertHouseWeb/waiter/error_low.jsp");

			} else if (account.getPayment() < totalprice) {
				order.setStatus("已支付");
				boolean result = orderService.update(order);

				if (result) {
					String content = order.getContent();
					waiterbean = (WaiterBean) session.getAttribute("waiter_waiterbean");
					Waiter waiter = waiterbean.getWaiter();
					List planlist = planService.findByHouseId(waiter.getHouseid());
					String[] contentlist = content.split(",");
					for (int i = 0; i < contentlist.length; i++) {
						String[] list = contentlist[i].split(" X");
						String name = list[0];
						int num = Integer.parseInt(list[1]);
						for (int j = 0; j < planlist.size(); j++) {
							Plan plan = (Plan) planlist.get(j);
							if (plan.getDessertname().equals(name)) {
								if ((plan.getAmount() - num) <= 0) {
									num = num - plan.getAmount();
									planService.delete(plan);
									continue;
								} else {
									plan.setAmount(plan.getAmount() - num);
									planService.update(plan);
									break;
								}
							}
						}
					}

					user.setReward(user.getReward() + totalprice);
					user.setConsume(user.getConsume() + totalprice);
					
					Double consume = user.getConsume();
					if (consume >= 100.0) {
						user.setLevel(1);
					} else if (consume >= 200.0) {
						user.setLevel(2);
					} else if (consume >= 400.0) {
						user.setLevel(3);
					} else if (consume >= 800.0) {
						user.setLevel(4);
					} else if (consume >= 1600.0) {
						user.setLevel(5);
					}

					account.setPayment(account.getPayment() - totalprice);
					accountService.update(account);
					result_1 = userService.update(user);

					if (result_1) {
						housebean = (HouseBean) session.getAttribute("waiter_housebean");
						House house = housebean.getHouse();

						List orderlist = orderService.findOrderByHouseId(house.getHouseid());
						List reallylist = new ArrayList();
						for (int i = 0; i < orderlist.size(); i++) {
							Order o = (Order) orderlist.get(i);
							if (o.getStatus().equals("未支付")) {
								reallylist.add(o);
							}
						}
						orderbean.setOrderlist(reallylist);
						session.setAttribute("waiter_orderbean", orderbean);

						response.sendRedirect("/DessertHouseWeb/waiter/reserve.jsp");
					} else {
						response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
					}
				}

			} else {
				order.setStatus("已支付");
				boolean result = orderService.update(order);
				if (result) {
					String content = order.getContent();
					waiterbean = (WaiterBean) session.getAttribute("waiter_waiterbean");
					Waiter waiter = waiterbean.getWaiter();
					List planlist = planService.findByHouseId(waiter.getHouseid());
					String[] contentlist = content.split(",");
					for (int i = 0; i < contentlist.length; i++) {
						String[] list = contentlist[i].split(" X");
						String name = list[0];
						int num = Integer.parseInt(list[1]);
						for (int j = 0; j < planlist.size(); j++) {
							Plan plan = (Plan) planlist.get(j);
							if (plan.getDessertname().equals(name)) {
								if ((plan.getAmount() - num) <= 0) {
									num = num - plan.getAmount();
									planService.delete(plan);
									continue;
								} else {
									plan.setAmount(plan.getAmount() - num);
									planService.update(plan);
									break;
								}
							}
						}
					}

					user.setReward(user.getReward() + totalprice);
					user.setConsume(user.getConsume() + totalprice);

					Double consume = user.getConsume();
					if (consume >= 100.0) {
						user.setLevel(1);
					} else if (consume >= 200.0) {
						user.setLevel(2);
					} else if (consume >= 400.0) {
						user.setLevel(3);
					} else if (consume >= 800.0) {
						user.setLevel(4);
					} else if (consume >= 1600.0) {
						user.setLevel(5);
					}

					account.setPayment(account.getPayment() - totalprice);
					accountService.update(account);
					result_1 = userService.update(user);

					if (result_1) {
						housebean = (HouseBean) session.getAttribute("waiter_housebean");
						House house = housebean.getHouse();

						List orderlist = orderService.findOrderByHouseId(house.getHouseid());
						List reallylist = new ArrayList();
						for (int i = 0; i < orderlist.size(); i++) {
							Order o = (Order) orderlist.get(i);
							if (o.getStatus().equals("未支付")) {
								reallylist.add(o);
							}
						}
						orderbean.setOrderlist(reallylist);
						session.setAttribute("waiter_orderbean", orderbean);

						response.sendRedirect("/DessertHouseWeb/waiter/reserve.jsp");
					} else {
						response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
					}
				}
			}

		} else {
			housebean = (HouseBean) session.getAttribute("waiter_housebean");
			House house = housebean.getHouse();

			List orderlist = orderService.findOrderByHouseId(house.getHouseid());
			List reallylist = new ArrayList();
			for (int i = 0; i < orderlist.size(); i++) {
				Order o = (Order) orderlist.get(i);
				if (o.getStatus().equals("未支付")) {
					reallylist.add(o);
				}
			}
			orderbean.setOrderlist(reallylist);
			session.setAttribute("waiter_orderbean", orderbean);

			response.sendRedirect("/DessertHouseWeb/waiter/reserve.jsp");

		}

	}

}
