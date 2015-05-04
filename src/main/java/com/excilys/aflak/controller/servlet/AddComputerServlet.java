package com.excilys.aflak.controller.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.TimeConvertor;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyService serviceCompany;
	private ComputerService serviceComputer;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
		super();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/applicationContext.xml", AddComputerServlet.class);

		serviceCompany = applicationContext.getBean(CompanyService.class);
		serviceComputer = applicationContext.getBean(ComputerService.class);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Company> listCompanies = serviceCompany.getAllCompanies();
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

		long companyId = Long.parseLong(request.getParameter("companies"));

		Company company = serviceCompany.findCompany(companyId);
		System.out.println(company.getId() + " name " + company.getName());
		Computer com = ComputerBuilder.createDefaultComputer()
				.withName(computerName).withIntroduced(introduced)
				.withDiscontinued(discontinued).withCompany(company).build();
		System.out.println(com.getId() + " " + com.getName());
		// new Computer(-1, computerName, introduced, discontinued, company);
		serviceComputer.createComputer(com);
		// redirection vers une url ,recharger la page
		// forward = > redirection jsp
		response.sendRedirect("index");

	}
}
