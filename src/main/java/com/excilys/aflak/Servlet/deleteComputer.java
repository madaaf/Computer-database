package com.excilys.aflak.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.service.ServiceComputer;

/**
 * Servlet implementation class deleteComputer
 */
@WebServlet("/deleteComputer")
public class deleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteComputer() {
		super();
		// TODO Auto-generated constructor stub
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
			ServiceComputer.SERVICE.deleteComputer(Long.parseLong(computerId));
		}

		response.sendRedirect("index?deletedSuccess");

	}
}
