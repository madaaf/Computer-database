package com.excilys.aflak.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ServiceComputer;
import com.excilys.aflak.utils.Mapper;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/index")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// this.limit = 10;
		// this.debut = 0;
		// this.fin = debut + 5;
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
		String search = null;

		if (request.getParameter("deletedSuccess") != null) {
			request.setAttribute("deletedSuccess", "deletedSuccess");
		}
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}

		if (request.getParameter("page") != null) {
			debut = Integer.parseInt(request.getParameter("page"));
			fin = debut + 5;
		}

		List<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
		int nbrComputers = 0;

		// si l'utilsateur tape une recherche
		if (request.getParameter("search") != null) {
			search = request.getParameter("search");
			// remplir la liste de computer en fonction de la recherche
			for (Computer computer : ServiceComputer.SERVICE
					.getSomeFiltredComputer(search, limit * debut, limit)) {
				listComputers.add(Mapper.computerToComputerDTO(computer));
			}
			// initialiser le nbr de computer en fct de la recherche
			nbrComputers = ServiceComputer.SERVICE
					.getSizeFiltredComputer(search);
		} else {
			// si l'utilisateur n'a rien rentrer dans la bar de recherche
			for (Computer computer : ServiceComputer.SERVICE.getSomeComputers(
					limit * debut, limit)) {
				// remplir toute la liste de computer
				listComputers.add(Mapper.computerToComputerDTO(computer));
			}
			// retourne le nbr de computer
			nbrComputers = ServiceComputer.SERVICE.getSizeTabComputers();
		}

		float nbrOfPagesF = (float) nbrComputers / (float) limit;
		int nbrOfPages = (int) Math.ceil(nbrOfPagesF);

		request.setAttribute("debut", debut);
		request.setAttribute("search", search);
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
