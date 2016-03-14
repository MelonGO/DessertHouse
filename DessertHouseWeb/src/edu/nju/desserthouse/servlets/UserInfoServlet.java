package edu.nju.desserthouse.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.AccountBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.model.Account;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.AccountManageService;
import edu.nju.desserthouse.service.UserManageService;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UserManageService userService;
	@EJB
	AccountManageService accountService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoServlet() {
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
		ServletContext context = getServletContext();
		UserBean userbean = new UserBean();
		AccountBean accountbean = new AccountBean();

		userbean = (UserBean) session.getAttribute("user_userinfo");
		User user = userbean.getUser();
		User u = userService.findById(user.getUserid());
		userbean.setUser(u);
		session.setAttribute("user_userinfo", userbean);
		
		Account account = accountService.find(u.getUserid());
		accountbean.setAccount(account);
		session.setAttribute("user_accountbean", accountbean);
		
		response.sendRedirect("/DessertHouseWeb/user/settings.jsp");
	}

}
