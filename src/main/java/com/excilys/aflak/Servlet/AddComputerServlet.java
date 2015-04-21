package com.excilys.aflak.Servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ServiceCompany;
import com.excilys.aflak.service.ServiceComputer;
import com.excilys.aflak.utils.TimeConvertor;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
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
		List<Company> listCompanies = ServiceCompany.SERVICE.getAllCompanies();
		request.setAttribute("listCompanies", listCompanies);

		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/addComputer.jsp")
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

		String computerName = request.getParameter("name");
		LocalDateTime introduced = TimeConvertor
				.convertStringToLocalDateTime(request
						.getParameter("introduced"));
		LocalDateTime discontinued = TimeConvertor
				.convertStringToLocalDateTime(request
						.getParameter("discontinued"));

		int companyId = Integer.parseInt(request.getParameter("companies"));

		Company company = ServiceCompany.SERVICE.findCompany(companyId);
		Computer com = new Computer(-1, computerName, introduced, discontinued,
				company);
		ServiceComputer.SERVICE.createComputer(com);
		// redirection vers une url ,recharger la page
		// forward = > redirection jsp
		response.sendRedirect("index");

	}
}
