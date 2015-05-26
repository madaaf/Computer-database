package com.excilys.aflak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customLogin")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "auth", required = false) String auth,
			Model model) {
		if (auth == null) {
			auth = "true";
		}
		model.addAttribute("auth", auth);
		return "customLogin";
	}

}
