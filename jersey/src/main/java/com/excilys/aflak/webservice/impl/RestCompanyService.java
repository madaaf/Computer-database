package com.excilys.aflak.webservice.impl;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.service.CompanyService;
import com.excilys.aflak.webservice.IRestService;

@RestController
@RequestMapping("/companies")
public class RestCompanyService implements IRestService<Company> {

	@Autowired
	private CompanyService service;

	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(value = "", method = RequestMethod.GET)
	@Override
	public List<Company> findAll() {
		return service.getAllCompanies();
	}

	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Override
	public Company find(@PathVariable("id") long id) {
		return service.findCompany(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public void delete(@PathVariable("id") long id) {
		service.deleteCompany(id);
	}

}
