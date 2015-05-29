package com.excilys.aflak.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.aflak.dao.ICrudDAO.Sort;
import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.dto.Page;
import com.excilys.aflak.dto.PageRequest;
import com.excilys.aflak.mapper.ComputerMapper;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.validator.Date.Pattern;
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

	/*
	 * @Valid test the PageValidator() because of @InitBinder Otherwise we also
	 * valid the page with the annotation in page. We handle the errors with
	 * BindingResult
	 */

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@Valid @ModelAttribute("Page") Page page,
			BindingResult result, ModelMap model) {
		Locale locale = LocaleContextHolder.getLocale();
		Pattern language = Pattern.map(locale.getLanguage());
		ComputerMapper mapper = new ComputerMapper(language);

		PageRequest request = PageRequest.Builder.builder()
				.filter(page.getFilter()).field(page.getField())
				.sort(Sort.valueOf(page.getSort()))
				.pageSize(page.getPageSize()).pageNumber(page.getPageNumber())
				.build();

		System.err.println("CONTROLLER");
		System.err.println(request.toString());
		List<ComputerDTO> computers = mapper.toListDto(serviceComputer
				.list(request));

		if (result.hasErrors()) {
			logger.debug("HAS RESULT ERRORS");
			// page.initialize();
			// page.setInvalidArgument("invalidArgument");
		}
		page.setTotalComputer(serviceComputer.count(page.getFilter()));
		page.setListComputers(computers);

		System.err.println(page.toString());
		model.addAttribute("pageS", page);

		return "dashboard";

	}
}
