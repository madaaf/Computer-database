package com.excilys.aflak.controller.servlet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.aflak.controller.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.Computer.ComputerBuilder;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.Mapper;
import com.excilys.aflak.utils.TimeConvertor;

@Controller
@RequestMapping("/editComputer")
public class EditComputerServlet {

	@Autowired
	private ComputerService serviceComputer;
	@Autowired
	private CompanyService serviceCompany;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, HttpServletResponse response) {

		long id = Long.parseLong(request.getParameter("id"));
		Computer computer = serviceComputer.findComputer(id);
		ComputerDTO dto = Mapper.computerToComputerDTO(computer);

		List<Company> listCompanies = new ArrayList<Company>();
		listCompanies = serviceCompany.getAllCompanies();

		request.setAttribute("listCompanies", listCompanies);
		request.setAttribute("computer", dto);

		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request,
			HttpServletResponse response) {

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
		serviceComputer.updateComputer(computer);
		return "index";
	}
}
