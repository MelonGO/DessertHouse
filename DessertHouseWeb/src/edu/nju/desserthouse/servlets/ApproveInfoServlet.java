package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.PlanBean;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.service.PlanManageService;

/**
 * Servlet implementation class ApproveInfoServlet
 */
@WebServlet("/ApproveInfo")
public class ApproveInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	PlanManageService planService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveInfoServlet() {
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

}
