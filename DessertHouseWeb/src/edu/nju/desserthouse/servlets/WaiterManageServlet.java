package edu.nju.desserthouse.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.desserthouse.action.business.HouseBean;
import edu.nju.desserthouse.action.business.WaiterBean;
import edu.nju.desserthouse.model.Waiter;
import edu.nju.desserthouse.service.HouseManageService;
import edu.nju.desserthouse.service.WaiterManageService;

/**
 * Servlet implementation class WaiterManageServlet
 */
@WebServlet("/WaiterManage")
public class WaiterManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	WaiterManageService waiterService;
	@EJB
	HouseManageService houseService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaiterManageServlet() {
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
		
		if (request.getParameter("manage") == null) {
			ServletContext context = getServletContext();
			HttpSession session = null;
			session = request.getSession(true);
			
			WaiterBean waiterbean = new WaiterBean();
			waiterbean.setWaiterlist(waiterService.findWaiterList());
			session.setAttribute("admin_listWaiter", waiterbean);
			
			HouseBean housebean = new HouseBean();
			housebean.setHouselist(houseService.findHouseList());
			session.setAttribute("admin_housebean", housebean);
			
			response.sendRedirect("/DessertHouseWeb/admin/waiterManage.jsp");
		} else {
			String manage = (String) request.getParameter("manage");
			switch (manage) {
			case "add":
				addWaiter(request, response);
				break;
			case "update":
				updateWaiter(request, response);
				break;
			case "delete":
				deleteWaiter(request, response);
				break;
			}
		}

	}

	private void addWaiter(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(false);
		WaiterBean waiterbean = new WaiterBean();

		String waitername = (String) request.getParameter("waitername");
		String waiterpassword = (String) request.getParameter("waiterpassword");
		String gender = (String) request.getParameter("gender");
		int houseid = Integer.parseInt(request.getParameter("houseid"));

		Waiter waiter = new Waiter();
		waiter.setWaitername(waitername);
		waiter.setWaiterpassword(waiterpassword);
		waiter.setGender(gender);
		waiter.setHouseid(houseid);

		boolean result = waiterService.save(waiter);
		if (result) {
			waiterbean.setWaiterlist(waiterService.findWaiterList());
			session.setAttribute("admin_listWaiter", waiterbean);
			response.sendRedirect("/DessertHouseWeb/admin/waiterManage.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/admin/error.jsp");
		}
	}

	private void updateWaiter(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(false);
		WaiterBean waiterbean = new WaiterBean();

		int waiterid = Integer.parseInt(request.getParameter("waiterid-refresh"));
		String waitername = (String) request.getParameter("waitername-refresh");
		String waiterpassword = (String) request.getParameter("waiterpassword-refresh");
		String gender = (String) request.getParameter("gender-refresh");
		int houseid = Integer.parseInt(request.getParameter("houseid-refresh"));

		boolean result = false;
		waiterbean = (WaiterBean) session.getAttribute("admin_listWaiter");
		List list = waiterbean.getWaiterlist();
		for (int i = 0; i < list.size(); i++) {
			Waiter waiter = (Waiter) list.get(i);
			if (waiter.getWaiterid() == waiterid) {
				waiter.setWaitername(waitername);
				waiter.setWaiterpassword(waiterpassword);
				waiter.setGender(gender);
				waiter.setHouseid(houseid);
				result = waiterService.update(waiter);
			}
		}

		if (result) {
			waiterbean.setWaiterlist(waiterService.findWaiterList());
			session.setAttribute("admin_listWaiter", waiterbean);
			response.sendRedirect("/DessertHouseWeb/admin/waiterManage.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/admin/error.jsp");
		}

	}

	private void deleteWaiter(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(false);
		WaiterBean waiterbean = new WaiterBean();

		int waiterid = Integer.parseInt(request.getParameter("deleteId"));
		boolean result = false;
		waiterbean = (WaiterBean) session.getAttribute("admin_listWaiter");
		List list = waiterbean.getWaiterlist();
		for (int i = 0; i < list.size(); i++) {
			Waiter waiter = (Waiter) list.get(i);
			if (waiter.getWaiterid() == waiterid) {
				result = waiterService.delete(waiter);
			}
		}

		if (result) {
			waiterbean.setWaiterlist(waiterService.findWaiterList());
			session.setAttribute("admin_listWaiter", waiterbean);
			response.sendRedirect("/DessertHouseWeb/admin/waiterManage.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/admin/error.jsp");
		}
	}

}
