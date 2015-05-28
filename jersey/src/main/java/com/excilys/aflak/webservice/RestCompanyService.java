package com.excilys.aflak.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.aflak.model.Company;
import com.excilys.aflak.service.CompanyService;

@RestController
@RequestMapping("rest/company")
public class RestCompanyService implements IRestService<Company> {

	@Autowired
	private CompanyService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findAll")
	@Override
	public List<Company> findAll() {
		return service.getAllCompanies();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("find/{id}")
	@Override
	public Company find(@PathVariable("id") long id) {
		return service.findCompany(id);
	}

	@Override
	public void create(Company obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Company obj) {
		// TODO Auto-generated method stub

	}

}
