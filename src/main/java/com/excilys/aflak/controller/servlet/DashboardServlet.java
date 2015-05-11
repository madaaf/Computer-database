package com.excilys.aflak.controller.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.aflak.controller.dto.Page;
import com.excilys.aflak.service.ComputerService;

@Controller
@RequestMapping("/index")
public class DashboardServlet {

	@Autowired
	private ComputerService serviceComputer;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@ModelAttribute("Page") Page page, ModelMap model) {
		page.validate(serviceComputer);
		model.addAttribute("pageS", page);
		System.err.println(page.toString());
		return "dashboard";

	}

}
