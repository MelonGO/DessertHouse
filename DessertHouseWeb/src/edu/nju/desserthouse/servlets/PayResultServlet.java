package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class PayResultServlet
 */
@WebServlet("/PayResult")
public class PayResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	AccountManageService accountService;
	@EJB
	UserManageService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayResultServlet() {
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
		
		String payType = (String) request.getParameter("paytype");
		switch (payType) {
		case "recharge":
			userRecharge(request, response);
			break;
		}

	}

	private void userRecharge(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(false);
		AccountBean accountbean = new AccountBean();
		UserBean userbean = new UserBean();

		int userid = Integer.parseInt(request.getParameter("userid"));
		Double money = Double.parseDouble(request.getParameter("money"));
		String password = (String) request.getParameter("password");

		Account account = accountService.find(userid);
		Double payment = account.getPayment();

		User user = userService.findById(userid);

		Double total = payment + money;

		if (money >= 200.0 && total >= 0 && user.getStatus().equals("已停止")) {
			user.setStatus("已激活");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = df.format(new Date());
			user.setCardtime(date);
			userService.update(user);
		}

		if (payment < 0 && user.getStatus().equals("已暂停")) {
			if (total >= 0) {
				user.setStatus("已激活");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = df.format(new Date());
				user.setCardtime(date);
				userService.update(user);
			}
		}

		account.setPayment(total);
		accountService.update(account);

		accountbean.setAccount(account);
		session.setAttribute("user_accountbean", accountbean);

		userbean.setUser(user);
		session.setAttribute("user_userinfo", userbean);

		response.sendRedirect("/DessertHouseWeb/user/settings.jsp");
	}

}
