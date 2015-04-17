package com.excilys.aflak.presentation;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.aflak.model.Computer;
import com.excilys.aflak.presentation.dto.ComputerDTO;
import com.excilys.aflak.service.ServiceComputer;
import com.excilys.aflak.utils.Mapper;
import com.excilys.aflak.utils.TimeConvertor;

/**
 * Servlet implementation class editComputerServlet
 */
@WebServlet("/editComputer")
public class editComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editComputerServlet() {
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
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Computer computer = ServiceComputer.findComputer(id);
		ComputerDTO dto = Mapper.computerToComputerDTO(computer);
		request.setAttribute("computer", dto);
		request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editComputer.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("editName");
		LocalDateTime introduced = TimeConvertor
				.convertStringToLocalDateTime(request
						.getParameter("editIntroduced"));

	}

}
