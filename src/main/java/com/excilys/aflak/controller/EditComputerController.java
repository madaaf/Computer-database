package com.excilys.aflak.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.aflak.controller.dto.ComputerDTO;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.utils.Mapper;

@Controller
@RequestMapping("/editComputer")
public class EditComputerController {

	@Autowired
	private ComputerService serviceComputer;
	@Autowired
	private CompanyService serviceCompany;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@RequestParam("id") String idCom, ModelMap model) {

		long id = Long.parseLong(idCom);
		Computer computer = serviceComputer.findComputer(id);
		ComputerDTO dto = Mapper.computerToComputerDTO(computer);

		List<Company> listCompanies = new ArrayList<Company>();
		listCompanies = serviceCompany.getAllCompanies();

		model.addAttribute("listCompanies", listCompanies);
		model.addAttribute("computer", dto);
		model.addAttribute("ComputerDTO", new ComputerDTO());

		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(@ModelAttribute("ComputerDTO") ComputerDTO computerDTO) {

		System.err.println(computerDTO.toString());
		Computer computer = Mapper.computerDTOToComputer(computerDTO);
		System.err.println(computer.toString());
		serviceComputer.updateComputer(computer);
		return "redirect:index";
	}
}
