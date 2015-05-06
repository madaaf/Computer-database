package com.excilys.aflak.controller.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Servlet implementation class Test
 */
@Controller
@RequestMapping({ "/dashboard" })
public class Test {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld(Model model) {
		model.addAttribute("message", "Hello World!");
		return "dashboard";
	}

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

}
