package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import edu.nju.desserthouse.action.business.AccountBean;
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
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/Payment")
public class PaymentServlet extends HttpServlet {
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
	public PaymentServlet() {
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
		
		String payType = (String) request.getParameter("pay");
		switch (payType) {
		case "recharge":
			userRecharge(request, response);
			break;
		case "cancel":
			vipCancel(request, response);
			break;
		case "order":
			createOrder(request, response);
			break;
		case "exchange":
			rewardExchange(request, response);
			break;
		}

	}

	private void rewardExchange(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		UserBean userbean = new UserBean();
		AccountBean accountbean = new AccountBean();

		int userid = Integer.parseInt(request.getParameter("userId"));
		User user = userService.findById(userid);
		Account account = accountService.find(userid);
		account.setPayment(account.getPayment() + user.getReward() / 10);
		user.setReward(0.0);

		boolean result = userService.update(user);
		boolean result_1 = accountService.update(account);

		if (result && result_1) {
			userbean.setUser(user);
			session.setAttribute("user_userinfo", userbean);

			accountbean.setAccount(account);
			session.setAttribute("user_accountbean", accountbean);

			response.sendRedirect("/DessertHouseWeb/user/settings.jsp");
		}

	}

	private void createOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		UserBean userbean = new UserBean();
		OrderBean orderbean = new OrderBean();
		WaiterBean waiterbean = new WaiterBean();
		HouseBean housebean = new HouseBean();

		int userid = Integer.parseInt(request.getParameter("userid"));
		if (userid == -1) {
			String content = (String) request.getParameter("content");
			Double totalprice = Double.parseDouble(request.getParameter("totalprice"));
			String status = "已支付";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());

			housebean = (HouseBean) session.getAttribute("waiter_housebean");
			House house = housebean.getHouse();

			Order order = new Order();
			order.setUserid(-1);
			order.setUsername("非会员");
			order.setHouseid(house.getHouseid());
			order.setHousename(house.getHousename());
			order.setContent(content);
			order.setPrice(totalprice);
			order.setStatus(status);
			order.setOrdertime(date);

			boolean result = orderService.save(order);

			if (result) {
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

				List orderlist = orderService.findAll();
				orderbean.setOrderlist(orderlist);
				session.setAttribute("waiter_orderbean", orderbean);

				List userlist = userService.findUserList();
				userbean.setListuser(userlist);
				session.setAttribute("waiter_userbean", userbean);

				response.setContentType("text/html; charset=utf-8");

				response.sendRedirect("/DessertHouseWeb/waiter/record.jsp");
			} else {
				response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
			}

		} else {
			String content = (String) request.getParameter("content");
			Double totalprice = Double.parseDouble(request.getParameter("totalprice"));
			String status = "已支付";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());

			housebean = (HouseBean) session.getAttribute("waiter_housebean");
			House house = housebean.getHouse();

			Order order = new Order();
			order.setUserid(userid);
			User user_1 = userService.findById(userid);
			order.setUsername(user_1.getUsername());
			order.setHouseid(house.getHouseid());
			order.setHousename(house.getHousename());
			order.setContent(content);
			order.setPrice(totalprice);
			order.setStatus(status);
			order.setOrdertime(date);

			User user = userService.findById(userid);
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

			Account account = accountService.find(userid);
			boolean result = false;
			boolean result_1 = false;
			if (account.getPayment() < totalprice) {
				response.sendRedirect("/DessertHouseWeb/waiter/error_low.jsp");

			} else {
				account.setPayment(account.getPayment() - totalprice);
				accountService.update(account);
				result_1 = userService.update(user);
				result = orderService.save(order);
			}

			if (result && result_1) {
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

				List userlist = userService.findUserList();
				userbean.setListuser(userlist);
				userbean.setUser(user);
				session.setAttribute("waiter_userbean", userbean);

				List orderlist = orderService.findOrderByUserId(userid);
				orderbean.setOrderlist(orderlist);
				session.setAttribute("waiter_orderbean", orderbean);

				response.sendRedirect("/DessertHouseWeb/waiter/record.jsp");
			} else {
				response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
			}
		}

	}

	private void userRecharge(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(true);
		AccountBean accountbean = new AccountBean();

		int userid = Integer.parseInt(request.getParameter("userId"));
		Account account = accountService.find(userid);
		accountbean.setAccount(account);
		session.setAttribute("user_accountbean", accountbean);

		context.getRequestDispatcher("/payment/recharge.jsp").forward(request, response);

	}

	private void vipCancel(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		UserBean userbean = new UserBean();

		int userid = Integer.parseInt(request.getParameter("userId"));
		User user = userService.findById(userid);
		user.setStatus("已停止");

		boolean result = userService.update(user);

		if (result) {
			userbean.setUser(user);
			session.setAttribute("user_userinfo", userbean);
			response.sendRedirect("/DessertHouseWeb/user/settings.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/user/error.jsp");
		}

	}

}
