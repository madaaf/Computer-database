package com.excilys.aflak.controller.servlet;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.aflak.controller.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.Mapper;

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
	public String doGet(
			@RequestParam(value = "listCompanies", required = false) List<Company> listCompanies,
			ModelMap map) {
		listCompanies = serviceCompany.getAllCompanies();
		map.addAttribute("listCompanies", listCompanies);

		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@ModelAttribute("computerDTO") ComputerDTO computerDTO)
			throws IOException {

		Computer com = Mapper.computerDTOToComputer(computerDTO);
		serviceComputer.createComputer(com);
		// redirection vers une url ,recharger la page
		// forward = > redirection jsp
		return "redirect:index";
		// response.sendRedirect("index");

	}
}
