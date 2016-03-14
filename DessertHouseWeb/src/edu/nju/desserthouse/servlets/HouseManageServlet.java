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
import edu.nju.desserthouse.model.House;
import edu.nju.desserthouse.service.HouseManageService;

/**
 * Servlet implementation class HouseManageServlet
 */
@WebServlet("/HouseManage")
public class HouseManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	HouseManageService houseService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HouseManageServlet() {
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
			HouseBean housebean = new HouseBean();
			housebean.setHouselist(houseService.findHouseList());
			session.setAttribute("admin_listHouse", housebean);
			response.sendRedirect("/DessertHouseWeb/admin/manage.jsp");
		} else {
			String manage = (String) request.getParameter("manage");
			switch (manage) {
			case "add":
				addHouse(request, response);
				break;
			case "update":
				updateHouse(request, response);
				break;
			case "delete":
				deleteHouse(request, response);
				break;
			}
		}

	}

	private void addHouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(false);
		HouseBean housebean = new HouseBean();

		String housename = (String) request.getParameter("housename");
		String address = (String) request.getParameter("address");
		String main = (String) request.getParameter("main");

		House house = new House();
		house.setHousename(housename);
		house.setAddress(address);
		house.setMain(main);

		boolean result = houseService.save(house);
		if (result) {
			housebean.setHouselist(houseService.findHouseList());
			session.setAttribute("admin_listHouse", housebean);
			response.sendRedirect("/DessertHouseWeb/admin/manage.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/admin/error.jsp");
		}
	}

	private void updateHouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(false);
		HouseBean housebean = new HouseBean();

		int houseid = Integer.parseInt(request.getParameter("houseid-refresh"));
		String housename = (String) request.getParameter("housename-refresh");
		String address = (String) request.getParameter("address-refresh");
		String main = (String) request.getParameter("main-refresh");

		boolean result = false;
		housebean = (HouseBean) session.getAttribute("admin_listHouse");
		List list = housebean.getHouselist();
		for (int i = 0; i < list.size(); i++) {
			House house = (House) list.get(i);
			if (house.getHouseid() == houseid) {
				house.setHousename(housename);
				house.setAddress(address);
				house.setMain(main);
				result = houseService.update(house);
			}
		}

		if (result) {
			housebean.setHouselist(houseService.findHouseList());
			session.setAttribute("admin_listHouse", housebean);
			response.sendRedirect("/DessertHouseWeb/admin/manage.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/admin/error.jsp");
		}

	}

	private void deleteHouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletContext context = getServletContext();
		HttpSession session = null;
		session = request.getSession(false);
		HouseBean housebean = new HouseBean();

		int houseid = Integer.parseInt(request.getParameter("deleteId"));
		boolean result = false;
		housebean = (HouseBean) session.getAttribute("admin_listHouse");
		List list = housebean.getHouselist();
		for (int i = 0; i < list.size(); i++) {
			House house = (House) list.get(i);
			if (house.getHouseid() == houseid) {
				result = houseService.delete(house);
			}
		}

		if (result) {
			housebean.setHouselist(houseService.findHouseList());
			session.setAttribute("admin_listHouse", housebean);
			response.sendRedirect("/DessertHouseWeb/admin/manage.jsp");
		} else {
			response.sendRedirect("/DessertHouseWeb/admin/error.jsp");
		}
	}

}
