package com.excilys.aflak.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.Mapper;
import com.excilys.aflak.utils.Validator;

/**
 * Servlet implementation class DashboardServlet
 * 
 * MODIFICATION SINCE HOME SAME BRANCHE 2
 */
@WebServlet("/index")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<String> limits = new ArrayList<String>(
			Arrays.asList("10", "50", "100"));

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int limit = 10;
		int debut = 0;
		int fin = debut + 5;

		String search = "";
		String colomn = null;
		String way = "ASC";
		int nbrComputers = 0;
		String page;
		// initialiser le nbr de computer en fct de la recherche
		nbrComputers = ComputerService.SERVICE.getSizeFiltredComputer(search);
		float nbrOfPagesF = (float) nbrComputers / (float) limit;
		int nbrOfPages = (int) Math.ceil(nbrOfPagesF);

		if (request.getParameter("deletedSuccess") != null) {
			request.setAttribute("deletedSuccess", "deletedSuccess");
		}
		if (request.getParameter("limit") != null
				&& limits.contains(request.getParameter("limit"))) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}

		page = request.getParameter("page");
		if (page != null) {
			if (Validator.isInteger(page)) {
				if (Long.parseLong(page) < nbrOfPages) {
					debut = Integer.parseInt(request.getParameter("page"));
					fin = debut + 5;
				}
			}
		}

		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		if (request.getParameter("way") != null) {
			way = request.getParameter("way");
			if (way.equals("ASC")) {
				way = "DESC";
			} else {
				way = "ASC";
			}
		}
		if (request.getParameter("colomn") != null) {
			colomn = request.getParameter("colomn");
		}

		List<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
		// remplir la liste de computer en fonction de la recherche
		if (limit * debut < nbrComputers) {
			for (Computer computer : ComputerService.SERVICE
					.getSomeFiltredComputer(search, colomn, way, limit * debut,
							limit)) {
				listComputers.add(Mapper.computerToComputerDTO(computer));
			}
		} else {
			for (Computer computer : ComputerService.SERVICE
					.getSomeFiltredComputer(search, colomn, way, 0, limit)) {
				listComputers.add(Mapper.computerToComputerDTO(computer));
			}
		}

		request.setAttribute("debut", debut);
		request.setAttribute("search", search);
		request.setAttribute("way", way);
		request.setAttribute("fin", fin);
		request.setAttribute("listComputers", listComputers);
		request.setAttribute("limit", limit);
		request.setAttribute("nbrOfPages", nbrOfPages);
		request.setAttribute("nbrComputers", nbrComputers);

		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/dashboard.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
