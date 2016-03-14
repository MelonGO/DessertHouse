package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.OrderBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.OrderManageService;

/**
 * Servlet implementation class UserRecordServlet
 */
@WebServlet("/UserRecord")
public class UserRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	OrderManageService orderService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRecordServlet() {
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
		OrderBean orderbean = new OrderBean();
		UserBean userbean = new UserBean();

		userbean = (UserBean) session.getAttribute("user_userinfo");
		User user = userbean.getUser();

		List orderlist = orderService.findOrderByUserId(user.getUserid());
		orderbean.setOrderlist(orderlist);
		session.setAttribute("user_orderbean", orderbean);

		response.sendRedirect("/DessertHouseWeb/user/record.jsp");
	}

}
