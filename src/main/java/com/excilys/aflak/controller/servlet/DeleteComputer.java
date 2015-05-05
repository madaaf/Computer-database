package com.excilys.aflak.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.aflak.service.ComputerService;

/**
 * Servlet implementation class deleteComputer
 */
@WebServlet("/deleteComputer")
public class DeleteComputer extends InitServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService serviceComputer;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputer() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String computersId[] = request.getParameter("selection").split(",");

		for (String computerId : computersId) {
			serviceComputer.deleteComputer(Long.parseLong(computerId));
		}

		response.sendRedirect("index?deletedSuccess");

	}
}
