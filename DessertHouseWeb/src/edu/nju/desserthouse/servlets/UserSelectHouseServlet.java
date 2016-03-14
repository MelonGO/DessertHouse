package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.OrderBean;
import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.service.PlanManageService;

/**
 * Servlet implementation class UserSelectHouseServlet
 */
@WebServlet("/SelectHouse")
public class UserSelectHouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	PlanManageService planService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSelectHouseServlet() {
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
		OrderBean orderbean = new OrderBean();

		String day = (String) session.getAttribute("day");

		int houseid = Integer.parseInt(request.getParameter("houseid"));
		session.setAttribute("user_houseid", houseid);
		List planlist;
		if (houseid == -1) {
			planlist = planService.findByDay(day);
		} else {
			planlist = planService.findByHouseIdAndDay(houseid, day);
		}
		planbean.setPlanlist(planlist);
		session.setAttribute("user_planbean", planbean);

		orderbean = (OrderBean) session.getAttribute("user_orderbean");
		session.setAttribute("user_orderbean", orderbean);

		response.sendRedirect("/DessertHouseWeb/user/reserve.jsp?house=" + houseid);

	}

}
