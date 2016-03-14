package edu.nju.desserthouse.servlets;

import java.io.IOException;
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

import edu.nju.desserthouse.action.business.DessertBean;
import edu.nju.desserthouse.action.business.HouseBean;
import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.action.business.WaiterBean;
import edu.nju.desserthouse.model.House;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.Waiter;
import edu.nju.desserthouse.service.DessertManageService;
import edu.nju.desserthouse.service.PlanManageService;

/**
 * Servlet implementation class PlanServlet
 */
@WebServlet("/Plan")
public class PlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	PlanManageService planService;
	@EJB
	DessertManageService dessertService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanServlet() {
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
		PlanBean planbean = new PlanBean();
		WaiterBean waiterbean = new WaiterBean();
		DessertBean dessertbean = new DessertBean();
		HouseBean housebean = new HouseBean();

		waiterbean = (WaiterBean) session.getAttribute("waiter_waiterbean");
		Waiter waiter = waiterbean.getWaiter();
		
		housebean = (HouseBean) session.getAttribute("waiter_housebean");
		House house = housebean.getHouse();

		List planlist = planService.findByHouseId(house.getHouseid());
		List reallylist = new ArrayList();
		for (int i = 0; i < planlist.size(); i++) {
			Plan p = (Plan) planlist.get(i);
			if (p.getStatus().equals("未通过")) {
				reallylist.add(p);
			}
		}
		planbean.setPlanlist(reallylist);
		session.setAttribute("waiter_planbean", planbean);

		List dessertlist = dessertService.findDessertList();
		dessertbean.setDessertlist(dessertlist);
		session.setAttribute("waiter_dessertbean", dessertbean);

		response.sendRedirect("/DessertHouseWeb/waiter/plan.jsp");
	}

}
