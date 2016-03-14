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

import edu.nju.desserthouse.action.business.HouseBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.action.business.WaiterBean;
import edu.nju.desserthouse.model.House;
import edu.nju.desserthouse.model.Waiter;
import edu.nju.desserthouse.service.HouseManageService;
import edu.nju.desserthouse.service.UserManageService;
import edu.nju.desserthouse.service.WaiterManageService;

/**
 * Servlet implementation class WaiterFindUserInfoServlet
 */
@WebServlet("/waiter.userinfo")
public class WaiterFindUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	HouseManageService houseService;
	@EJB
	UserManageService userService;
	@EJB
	WaiterManageService waiterService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaiterFindUserInfoServlet() {
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
		WaiterBean waiterbean = new WaiterBean();
		HouseBean housebean = new HouseBean();
		UserBean userbean = new UserBean();
		
		waiterbean = (WaiterBean) session.getAttribute("waiter_waiterbean");
		Waiter waiter = waiterbean.getWaiter();
		
		int houseid = waiter.getHouseid();
		House house = houseService.findHouseById(houseid);
		housebean.setHouse(house);
		session.setAttribute("waiter_housebean", housebean);

		List listuser = userService.findUserList();
		userbean.setListuser(listuser);
		session.setAttribute("waiter_userbean", userbean);

		response.sendRedirect("/DessertHouseWeb/waiter/userinfo.jsp");
	}
}
