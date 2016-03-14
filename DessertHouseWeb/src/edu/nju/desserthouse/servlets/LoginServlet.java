package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import edu.nju.desserthouse.action.business.AdminBean;
import edu.nju.desserthouse.action.business.HouseBean;
import edu.nju.desserthouse.action.business.ManagerBean;
import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.action.business.UserBean;
import edu.nju.desserthouse.action.business.WaiterBean;
import edu.nju.desserthouse.model.Account;
import edu.nju.desserthouse.model.Admin;
import edu.nju.desserthouse.model.House;
import edu.nju.desserthouse.model.Manager;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.model.Waiter;
import edu.nju.desserthouse.service.AccountManageService;
import edu.nju.desserthouse.service.AdminManageService;
import edu.nju.desserthouse.service.HouseManageService;
import edu.nju.desserthouse.service.ManagerManageService;
import edu.nju.desserthouse.service.PlanManageService;
import edu.nju.desserthouse.service.UserManageService;
import edu.nju.desserthouse.service.WaiterManageService;
import edu.nju.desserthouse.timer.CheckUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	UserManageService userService;
	@EJB
	AdminManageService adminService;
	@EJB
	HouseManageService houseService;
	@EJB
	AccountManageService accountService;
	@EJB
	WaiterManageService waiterService;
	@EJB
	ManagerManageService managerService;
	@EJB
	PlanManageService planService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		AdminBean adminbean = new AdminBean();
		HouseBean housebean = new HouseBean();
		AccountBean accountbean = new AccountBean();
		WaiterBean waiterbean = new WaiterBean();
		ManagerBean managerbean = new ManagerBean();
		PlanBean planbean = new PlanBean();

		String type = (String) request.getParameter("type");
		String name = (String) request.getParameter("name");
		String password = (String) request.getParameter("password");
		switch (type) {
		case "user":
			User user = userService.find(name, password);
			if (user != null) {
				userbean.setUser(user);
				session.setAttribute("user_userinfo", userbean);

				Account account = accountService.find(user.getUserid());
				accountbean.setAccount(account);
				session.setAttribute("user_accountbean", accountbean);

				CheckUser cu = new CheckUser(user, account);
				try {
					User u = cu.CheckUser();
					userService.update(u);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				response.sendRedirect("/DessertHouseWeb/user/settings.jsp");
			} else {
				response.sendRedirect("/DessertHouseWeb/user/error.jsp");
			}
			break;
		case "waiter":
			Waiter waiter = waiterService.find(name, password);
			if (waiter != null) {
				waiterbean.setWaiter(waiter);
				session.setAttribute("waiter_waiterbean", waiterbean);

				int houseid = waiter.getHouseid();
				House house = houseService.findHouseById(houseid);
				housebean.setHouse(house);
				session.setAttribute("waiter_housebean", housebean);

				List listuser = userService.findUserList();
				userbean.setListuser(listuser);
				session.setAttribute("waiter_userbean", userbean);

				response.sendRedirect("/DessertHouseWeb/waiter/userinfo.jsp");
			} else {
				response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
			}
			break;
		case "manager":
			Manager manager = managerService.find(name, password);
			if (manager != null) {
				managerbean.setManager(manager);
				session.setAttribute("manager_managerbean", managerbean);

				List planlist = planService.findPlanList();
				List reallylist = new ArrayList();
				for (int i = 0; i < planlist.size(); i++) {
					Plan plan = (Plan) planlist.get(i);
					if (plan.getStatus().equals("未通过")) {
						reallylist.add(plan);
					}
				}
				planbean.setPlanlist(reallylist);
				session.setAttribute("manager_planbean", planbean);

				response.sendRedirect("/DessertHouseWeb/manager/manage.jsp");
			}
			break;
		case "admin":
			Admin admin = adminService.find(name, password);
			if (admin != null) {
				adminbean.setAdmin(admin);
				session.setAttribute("adminbean", adminbean);
				housebean.setHouselist(houseService.findHouseList());
				session.setAttribute("admin_listHouse", housebean);
				response.sendRedirect("/DessertHouseWeb/admin/manage.jsp");
			} else {
				response.sendRedirect("/DessertHouseWeb/admin/error.jsp");
			}
			break;
		}

	}

}
