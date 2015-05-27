package com.excilys.aflak.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.aflak.binding.dto.ComputerDTO;
import com.excilys.aflak.mapper.ComputerMapper;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.validator.Date.Pattern;

/**
 * Servlet implementation class AddComputerServlet
 */
@Controller
@RequestMapping("/addComputer")
public class AddComputerController {

	@Autowired
	private CompanyService serviceCompany;
	@Autowired
	private ComputerService serviceComputer;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(Model model) {
		List<Company> listCompanies = serviceCompany.getAllCompanies();
		model.addAttribute("listCompanies", listCompanies);
		model.addAttribute("computerDTO", new ComputerDTO());
		return "addComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@Valid @ModelAttribute ComputerDTO computerDTO,
			BindingResult result, Model model) throws IOException {

		Locale locale = LocaleContextHolder.getLocale();
		Pattern language = Pattern.map(locale.getLanguage());
		System.err.println("servlet add " + language.name());
		// if @Valid find errors in computerDTO annotation
		// errors are send in BindingResult
		if (result.hasErrors()) {
			List<Company> listCompanies = serviceCompany.getAllCompanies();
			model.addAttribute("listCompanies", listCompanies);
			return "addComputer";
		} else {
			Computer com = ComputerMapper.fromDto(computerDTO, language);
			System.err.println(com.toString());
			serviceComputer.create(com);
			// redirection vers une url ,recharger la page
			// forward = > redirection jsp
			return "redirect:index";
			// response.sendRedirect("index");
		}

	}
}
