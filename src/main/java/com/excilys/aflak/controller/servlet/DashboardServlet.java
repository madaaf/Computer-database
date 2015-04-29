package com.excilys.aflak.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.controller.dto.ComputerDTO;
import com.excilys.aflak.controller.dto.Page;
import com.excilys.aflak.controller.dto.Page.PageBuilder;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.Mapper;

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

		Page page = PageBuilder.getDefaultPage().build();

		page.setSearch(request.getParameter("search"));
		page.setNbrComputers(ComputerService.SERVICE
				.getSizeFiltredComputer(page.getSearch()));

		float nbrOfPagesF = (float) page.getNbrComputers()
				/ (float) page.getLimit();

		page.setNbrOfPages((int) Math.ceil(nbrOfPagesF));

		if (request.getParameter("deletedSuccess") != null) {
			request.setAttribute("deletedSuccess", "deletedSuccess");
		}
		if (request.getParameter("limit") != null
				&& limits.contains(request.getParameter("limit"))) {
			page.setLimit(Integer.parseInt(request.getParameter("limit")));
		}

		if (request.getParameter("pageNum") != null) {
			page.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
			if (page.getPageNum() < page.getNbrOfPages()) {
				page.setStart(Integer.parseInt(request.getParameter("pageNum")));
				page.setEnd(page.getStart() + 5);
			}
		}

		if (request.getParameter("way") != null) {
			page.setWay(request.getParameter("way"));

			if (page.getWay().equals("ASC")) {
				page.setWay("DESC");
			} else {
				page.setWay("ASC");
			}
		}

		page.setColomn(request.getParameter("colomn"));

		List<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
		// remplir la liste de computer en fonction de la recherche
		if (page.getLimit() * page.getStart() < page.getNbrComputers()) {
			for (Computer computer : ComputerService.SERVICE
					.getSomeFiltredComputer(page.getSearch(), page.getColomn(),
							page.getWay(), page.getLimit() * page.getStart(),
							page.getLimit())) {
				listComputers.add(Mapper.computerToComputerDTO(computer));
			}
		} else {
			for (Computer computer : ComputerService.SERVICE
					.getSomeFiltredComputer(page.getSearch(), page.getColomn(),
							page.getWay(), 0, page.getLimit())) {
				listComputers.add(Mapper.computerToComputerDTO(computer));
			}
		}
		page.setListComputers(listComputers);

		request.setAttribute("pageS", page);

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
