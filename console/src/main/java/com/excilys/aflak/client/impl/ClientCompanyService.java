package com.excilys.aflak.client.impl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.aflak.client.IClientService;
import com.excilys.aflak.model.Company;

@Component
public class ClientCompanyService implements IClientService<Company> {
	private static final String ROOT = "http://localhost:8080/jersey/rest";
	private static final String COMPANY = "/company";

	Logger logger = LoggerFactory.getLogger(ClientCompanyService.class);

	@Override
	public List<Company> list() {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPANY + "/list");
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		logger.debug("RESPONSE " + response.toString());
		List<Company> companies = response
				.readEntity(new GenericType<List<Company>>() {
				});
		return companies;
	}

	@Override
	public Company find(Long id) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPANY + "/" + id);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		Company company = response.readEntity(Company.class);
		return company;
	}

	@Override
	public void create(Company o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Company o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
