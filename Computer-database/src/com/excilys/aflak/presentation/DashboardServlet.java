package com.excilys.aflak.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.model.Computer;
import com.excilys.aflak.presentation.dto.ComputerDTO;
import com.excilys.aflak.service.ServiceComputer;
import com.excilys.aflak.utils.Mapper;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/index")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int limit;
	private int debut;
	private int fin;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		this.limit = 10;
		this.debut = 0;
		this.fin = debut + 5;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int nbrComputers = ServiceComputer.getAllComputers().size();
		float nbrOfPagesF = (float) nbrComputers / (float) limit;
		int nbrOfPages = (int) Math.ceil(nbrOfPagesF);

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}

		if (request.getParameter("page") != null) {
			debut = Integer.parseInt(request.getParameter("page"));
			fin = debut + 5;
		}

		request.setAttribute("debut", debut);
		request.setAttribute("fin", fin);

		System.out.println("debu " + debut);
		System.out.println("fin " + fin);

		// TODO Auto-generated method stub
		List<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
		for (Computer computer : ServiceComputer.getSomeComputers(
				limit * debut, limit)) {
			listComputers.add(Mapper.computerToComputerDTO(computer));
		}
		request.setAttribute("listComputers", listComputers);
		request.setAttribute("nbrOfPages", nbrOfPages);

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
