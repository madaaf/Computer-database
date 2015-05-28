package com.excilys.aflak.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.aflak.service.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputerController {

	@Autowired
	private ComputerService serviceComputer;

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@RequestParam("selection") String selection)
			throws IOException {
		System.err.println("DELETE");
		String computersId[] = selection.split(",");

		for (String computerId : computersId) {
			serviceComputer.delete(Long.parseLong(computerId));
		}
		return "redirect:index?deletedSuccess";
		// response.sendRedirect("index?deletedSuccess");

	}
}
