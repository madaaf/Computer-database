package com.excilys.aflak.Servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ServiceCompany;
import com.excilys.aflak.service.ServiceComputer;
import com.excilys.aflak.utils.Mapper;
import com.excilys.aflak.utils.TimeConvertor;

/**
 * Servlet implementation class editComputerServlet
 */
@WebServlet("/editComputer")
public class editComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id = Long.parseLong(request.getParameter("id"));
		Computer computer = ServiceComputer.SERVICE.findComputer(id);
		ComputerDTO dto = Mapper.computerToComputerDTO(computer);

		List<Company> listCompanies = new ArrayList<Company>();
		listCompanies = ServiceCompany.SERVICE.getAllCompanies();

		request.setAttribute("listCompanies", listCompanies);
		request.setAttribute("computer", dto);
		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("computerId"));
		String name = request.getParameter("editName");
		LocalDateTime introduced = TimeConvertor
				.convertStringToLocalDateTime(request
						.getParameter("editIntroduced"));
		LocalDateTime discontinued = TimeConvertor
				.convertStringToLocalDateTime(request
						.getParameter("editDiscontinued"));
		long companyId = Long.parseLong(request.getParameter("editCompanyId"));
		Company company = ServiceCompany.SERVICE.findCompany(companyId);
		Computer computer = new Computer(id, name, introduced, discontinued,
				company);
		ServiceComputer.SERVICE.updateComputer(computer);
		response.sendRedirect("index");
	}
}
