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
 * Servlet implementation class UserRegisterServlets
 */
@WebServlet("/user.register")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UserManageService userService;
	@EJB
	AccountManageService accountService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
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
		ServletContext context = getServletContext();
		UserBean userbean = new UserBean();
		AccountBean accountbean = new AccountBean();

		String username = (String) request.getParameter("username");
		String userpassword = (String) request.getParameter("userpassword");
		String gender = (String) request.getParameter("gender");
		String status = "已停止";
		int cardid = 1000000 + (int) (Math.random() * 9000000);
		Double reward = 0.0;
		int level = 0;
		int age = Integer.parseInt(request.getParameter("age"));
		String address = (String) request.getParameter("address");
		Double consume = 0.0;

		User user = new User();
		user.setUsername(username);
		user.setUserpassword(userpassword);
		user.setGender(gender);
		user.setStatus(status);
		user.setCardid(cardid);
		user.setCardtime("");
		user.setReward(reward);
		user.setLevel(level);
		user.setAge(age);
		user.setAddress(address);
		user.setConsume(consume);

		boolean result = userService.register(user);

		if (result) {
			User newuser = userService.find(username, userpassword);
			Account account = new Account();
			account.setUserid(newuser.getUserid());
			account.setPayment(0.0);
			boolean result_1 = accountService.save(account);
			accountbean.setAccount(account);
			session.setAttribute("user_accountbean", accountbean);
			
			userbean.setUser(newuser);
			session.setAttribute("user_userinfo", userbean);
			response.sendRedirect("/DessertHouseWeb/user/settings.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/user/error.jsp");
		}

	}

}
