package com.excilys.aflak.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.aflak.controller.dto.Page;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.validator.PageValidator;

@Controller
@RequestMapping({ "/index", "/" })
public class DashboardController {

	private static Logger logger = LoggerFactory
			.getLogger(DashboardController.class);

	@Autowired
	private ComputerService serviceComputer;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new PageValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@Valid @ModelAttribute("Page") Page page,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			logger.debug("HAS RESULT ERRORS");
			page.initialize();
			page.setInvalidArgument("invalidArgument");
		}
		page.populate(serviceComputer);

		model.addAttribute("pageS", page);

		return "dashboard";

	}

}
