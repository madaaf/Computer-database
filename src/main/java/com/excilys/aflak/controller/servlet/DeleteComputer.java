package com.excilys.aflak.controller.servlet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.aflak.service.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputer {

	@Autowired
	private ComputerService serviceComputer;

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam("selection") String selection)
			throws IOException {

		String computersId[] = selection.split(",");

		for (String computerId : computersId) {
			serviceComputer.deleteComputer(Long.parseLong(computerId));
		}
		return "redirect:index?deletedSuccess";
		// response.sendRedirect("index?deletedSuccess");

	}
}
