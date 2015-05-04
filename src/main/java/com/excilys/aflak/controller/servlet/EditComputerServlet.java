package com.excilys.aflak.controller.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.aflak.controller.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.Mapper;
import com.excilys.aflak.utils.TimeConvertor;

/**
 * Servlet implementation class editComputerServlet
 */
@WebServlet("/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService serviceComputer;
	private CompanyService serviceCompany;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() {
		super();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/applicationContext.xml", EditComputerServlet.class);

		serviceComputer = applicationContext.getBean(ComputerService.class);
		serviceCompany = applicationContext.getBean(CompanyService.class);
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
		Computer computer = serviceComputer.findComputer(id);
		ComputerDTO dto = Mapper.computerToComputerDTO(computer);

		List<Company> listCompanies = new ArrayList<Company>();
		listCompanies = serviceCompany.getAllCompanies();

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
		String name = request.getParameter("name");
		LocalDateTime introduced = TimeConvertor
				.convertStringToLocalDateTime(request
						.getParameter("introduced"));
		LocalDateTime discontinued = TimeConvertor
				.convertStringToLocalDateTime(request
						.getParameter("discontinued"));
		long companyId = Long.parseLong(request.getParameter("companies"));
		Company company = serviceCompany.findCompany(companyId);
		Computer computer = ComputerBuilder.createDefaultComputer().withId(id)
				.withName(name).withIntroduced(introduced)
				.withDiscontinued(discontinued).withCompany(company).build();
		// new Computer(id, name, introduced, discontinued,company);
		serviceComputer.updateComputer(computer);
		response.sendRedirect("index");
	}
}
