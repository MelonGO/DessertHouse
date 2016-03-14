package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.AccountBean;
import edu.nju.desserthouse.action.business.AdminBean;
import edu.nju.desserthouse.action.business.HouseBean;
import edu.nju.desserthouse.action.business.ManagerBean;
import edu.nju.desserthouse.action.business.OrderBean;
import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.action.business.WaiterBean;
import edu.nju.desserthouse.service.HouseManageService;
import edu.nju.desserthouse.service.OrderManageService;
import edu.nju.desserthouse.service.PlanManageService;
import edu.nju.desserthouse.service.UserManageService;
import edu.nju.desserthouse.service.WaiterManageService;

/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/Statistics")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UserManageService userService;
	@EJB
	HouseManageService houseService;
	@EJB
	WaiterManageService waiterService;
	@EJB
	PlanManageService planService;
	@EJB
	OrderManageService orderService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatisticsServlet() {
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
		HouseBean housebean = new HouseBean();
		AccountBean accountbean = new AccountBean();
		WaiterBean waiterbean = new WaiterBean();
		ManagerBean managerbean = new ManagerBean();
		PlanBean planbean = new PlanBean();
		OrderBean orderbean = new OrderBean();
		
		List userlist = userService.findUserList();
		userbean.setListuser(userlist);
		session.setAttribute("manager_userbean", userbean);
		
		List orderlist = orderService.findAll();
		orderbean.setOrderlist(orderlist);
		session.setAttribute("manager_orderbean", orderbean);
		
		List houselist = houseService.findHouseList();
		housebean.setHouselist(houselist);
		session.setAttribute("manager_housebean", housebean);
		
		response.sendRedirect("/DessertHouseWeb/manager/statistics.jsp");
	}

}
