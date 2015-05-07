package com.excilys.aflak.controller.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.aflak.service.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputer {

	@Autowired
	private ComputerService serviceComputer;

	@RequestMapping(method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String computersId[] = request.getParameter("selection").split(",");

		for (String computerId : computersId) {
			serviceComputer.deleteComputer(Long.parseLong(computerId));
		}

		response.sendRedirect("index?deletedSuccess");

	}
}
