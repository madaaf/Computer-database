package com.excilys.aflak.controller.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.TimeConvertor;

/**
 * Servlet implementation class AddComputerServlet
 */
@Controller
@RequestMapping("/addComputer")
public class AddComputerServlet {

	@Autowired
	private CompanyService serviceCompany;
	@Autowired
	private ComputerService serviceComputer;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		List<Company> listCompanies = serviceCompany.getAllCompanies();
		request.setAttribute("listCompanies", listCompanies);

		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

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
