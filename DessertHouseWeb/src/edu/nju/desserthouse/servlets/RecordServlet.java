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
import edu.nju.desserthouse.service.OrderManageService;
import edu.nju.desserthouse.service.UserManageService;

/**
 * Servlet implementation class RecordServlet
 */
@WebServlet("/Record")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	OrderManageService orderService;
	@EJB
	UserManageService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordServlet() {
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
		session = request.getSession(true);
		UserBean userbean = new UserBean();
		OrderBean orderbean = new OrderBean();

		if (request.getParameter("userid") != null) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			List orderlist;
			if (userid == -1) {
				orderlist = orderService.findAll();
			} else {
				orderlist = orderService.findOrderByUserId(userid);
			}
			orderbean.setOrderlist(orderlist);
			session.setAttribute("waiter_orderbean", orderbean);

			List userlist = userService.findUserList();
			userbean.setListuser(userlist);
			session.setAttribute("waiter_userbean", userbean);

			response.sendRedirect("/DessertHouseWeb/waiter/record.jsp");
		} else {
			List orderlist = orderService.findAll();
			orderbean.setOrderlist(orderlist);
			session.setAttribute("waiter_orderbean", orderbean);

			List userlist = userService.findUserList();
			userbean.setListuser(userlist);
			session.setAttribute("waiter_userbean", userbean);

			response.sendRedirect("/DessertHouseWeb/waiter/record.jsp");
		}

	}

}
