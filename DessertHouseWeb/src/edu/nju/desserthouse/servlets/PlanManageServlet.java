package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.model.Dessert;
import edu.nju.desserthouse.model.House;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.Waiter;
import edu.nju.desserthouse.service.DessertManageService;
import edu.nju.desserthouse.service.HouseManageService;
import edu.nju.desserthouse.service.PlanManageService;
import edu.nju.desserthouse.service.WaiterManageService;

/**
 * Servlet implementation class PlanManageServlet
 */
@WebServlet("/PlanManage")
public class PlanManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	DessertManageService dessertService;
	@EJB
	PlanManageService planService;
	@EJB
	WaiterManageService waiterService;
	@EJB
	HouseManageService houseService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanManageServlet() {
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
		
		String manage = (String) request.getParameter("manage");
		switch (manage) {
		case "add":
			addPlan(request, response);
			break;
		case "update":
			updatePlan(request, response);
			break;
		case "delete":
			deletePlan(request, response);
			break;
		}
	}

	private void addPlan(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		PlanBean planbean = new PlanBean();

		int waiterid = Integer.parseInt(request.getParameter("waiterid"));
		int houseid = Integer.parseInt(request.getParameter("houseid"));
		int dessertid = Integer.parseInt(request.getParameter("dessertid"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String day = (String) request.getParameter("day");

		Waiter waiter = waiterService.findById(waiterid);
		House house = houseService.findHouseById(houseid);

		Dessert dessert = dessertService.findById(dessertid);
		String name = dessert.getDessertname();
		Double price = dessert.getPrice();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());

		Plan plan = new Plan();
		plan.setWaiterid(waiterid);
		plan.setWaitername(waiter.getWaitername());
		plan.setHouseid(houseid);
		plan.setHousename(house.getHousename());
		plan.setDessertid(dessertid);
		plan.setDessertname(name);
		plan.setDessertprice(price);
		plan.setStatus("未通过");
		plan.setDay(day);
		plan.setAmount(amount);
		plan.setPlantime(date);

		boolean result = planService.save(plan);
		if (result) {
			List planlist = planService.findPlanList();
			List reallylist = new ArrayList();
			for (int i = 0; i < planlist.size(); i++) {
				Plan p = (Plan) planlist.get(i);
				if (p.getStatus().equals("未通过")) {
					reallylist.add(p);
				}
			}
			planbean.setPlanlist(reallylist);
			session.setAttribute("waiter_planbean", planbean);

			response.sendRedirect("/DessertHouseWeb/waiter/plan.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
		}

	}

	private void updatePlan(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		PlanBean planbean = new PlanBean();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());

		int planid = Integer.parseInt(request.getParameter("planid-refresh"));
		int dessertid = Integer.parseInt(request.getParameter("dessertid-refresh"));
		int amount = Integer.parseInt(request.getParameter("amount-refresh"));
		String day = (String) request.getParameter("day-refresh");

		Plan plan = planService.findById(planid);

		Dessert dessert = dessertService.findById(dessertid);
		String name = dessert.getDessertname();
		Double price = dessert.getPrice();

		plan.setDessertid(dessertid);
		plan.setDessertname(name);
		plan.setDessertprice(price);
		plan.setAmount(amount);
		plan.setDay(day);
		plan.setPlantime(date);

		boolean result = planService.update(plan);

		if (result) {
			List planlist = planService.findPlanList();
			List reallylist = new ArrayList();
			for (int i = 0; i < planlist.size(); i++) {
				Plan p = (Plan) planlist.get(i);
				if (p.getStatus().equals("未通过")) {
					reallylist.add(p);
				}
			}
			planbean.setPlanlist(reallylist);
			session.setAttribute("waiter_planbean", planbean);

			response.sendRedirect("/DessertHouseWeb/waiter/plan.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
		}

	}

	private void deletePlan(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = null;
		session = request.getSession(false);
		PlanBean planbean = new PlanBean();

		int planid = Integer.parseInt(request.getParameter("deleteId"));
		Plan plan = planService.findById(planid);

		boolean result = planService.delete(plan);

		if (result) {
			List planlist = planService.findPlanList();
			List reallylist = new ArrayList();
			for (int i = 0; i < planlist.size(); i++) {
				Plan p = (Plan) planlist.get(i);
				if (p.getStatus().equals("未通过")) {
					reallylist.add(p);
				}
			}
			planbean.setPlanlist(reallylist);
			session.setAttribute("waiter_planbean", planbean);

			response.sendRedirect("/DessertHouseWeb/waiter/plan.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/waiter/error.jsp");
		}

	}

}
