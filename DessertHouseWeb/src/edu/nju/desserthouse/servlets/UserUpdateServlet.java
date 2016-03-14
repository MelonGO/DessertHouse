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

import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.UserManageService;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/user.update")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UserManageService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = null;
		session = request.getSession(false);
		ServletContext context = getServletContext();
		UserBean userbean= new UserBean();

		String username = (String) request.getParameter("username");
		String gender = (String) request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = (String) request.getParameter("address");

		userbean = (UserBean)session.getAttribute("user_userinfo");
		User user = userbean.getUser();
		user.setUsername(username);
		user.setGender(gender);
		user.setAge(age);
		user.setAddress(address);

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
