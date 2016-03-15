package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import edu.nju.desserthouse.model.Order;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.User;
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

		String[] ageData = new String[4];
		double n1 = 0.0;
		double n2 = 0.0;
		double n3 = 0.0;
		double n4 = 0.0;
		for (int i = 0; i < userlist.size(); i++) {
			User user = (User) userlist.get(i);
			int age = user.getAge();
			if (0 < age && age <= 15) {
				n1++;
			} else if (15 < age && age <= 30) {
				n2++;
			} else if (30 < age && age <= 60) {
				n3++;
			} else if (60 < age) {
				n4++;
			}
		}
		ageData[0] = n1 / (n1 + n2 + n3 + n4) * 100 + "%";
		ageData[1] = n2 / (n1 + n2 + n3 + n4) * 100 + "%";
		ageData[2] = n3 / (n1 + n2 + n3 + n4) * 100 + "%";
		ageData[3] = n4 / (n1 + n2 + n3 + n4) * 100 + "%";
		session.setAttribute("ageData", ageData);

		String[] genderData = new String[2];
		double g1 = 0.0;
		double g2 = 0.0;
		for (int i = 0; i < userlist.size(); i++) {
			User user = (User) userlist.get(i);
			String gender = user.getGender();
			if (gender.equals("male")) {
				g1++;
			} else {
				g2++;
			}
		}
		genderData[0] = g1 / (g1 + g2) * 100 + "%";
		genderData[1] = g2 / (g1 + g2) * 100 + "%";
		session.setAttribute("genderData", genderData);

		HashMap<String, Integer> dessertRank = new HashMap<String, Integer>();
		List orderlist = orderService.findAll();
		for (int i = 0; i < orderlist.size(); i++) {
			Order order = (Order) orderlist.get(i);
			String content = order.getContent();
			String[] contentlist = content.split(",");
			for (int j = 0; j < contentlist.length; j++) {
				String[] list = contentlist[j].split(" X");
				String name = list[0];
				int num = Integer.parseInt(list[1]);
				if (dessertRank.get(name) == null) {
					dessertRank.put(name, num);
				} else {
					dessertRank.put(name, dessertRank.get(name) + num);
				}
			}
		}
		List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(dessertRank.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
				// return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		session.setAttribute("dessertRank", infoIds);

		orderbean.setOrderlist(orderlist);
		session.setAttribute("manager_orderbean", orderbean);

		List houselist = houseService.findHouseList();
		housebean.setHouselist(houselist);
		session.setAttribute("manager_housebean", housebean);

		response.sendRedirect("/DessertHouseWeb/manager/statistics.jsp");
	}

}
