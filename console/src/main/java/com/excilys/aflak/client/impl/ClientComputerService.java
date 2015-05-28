package com.excilys.aflak.client.impl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.aflak.client.IClientService;
import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.mapper.ComputerMapper;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.validator.Date.Pattern;

@Component
public class ClientComputerService implements IClientService<Computer> {

	private static final String ROOT = "http://localhost:8080/webservice/rest";
	private static final String COMPUTER = "/computer";
	private static final String DELETE = "/delete";
	private static final String CREATE = "/create";
	private static final String UPDATE = "/update";

	Logger logger = LoggerFactory.getLogger(ClientComputerService.class);

	ComputerMapper mapper = new ComputerMapper(Pattern.FR);

	@Override
	public List<Computer> list() {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPUTER + "/list");
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		List<ComputerDTO> computers = response
				.readEntity(new GenericType<List<ComputerDTO>>() {
				});
		return mapper.fromListDto(computers);
	}

	@Override
	public Computer find(Long id) {
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		WebTarget webTarget = client.target(ROOT + COMPUTER + "/" + id);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		ComputerDTO computerDto = response.readEntity(ComputerDTO.class);
		return mapper.fromDto(computerDto);
	}

	@Override
	public void create(Computer o) {
		ComputerDTO comp = mapper.toDto(o);
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		client.target(ROOT + COMPUTER + CREATE).request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(comp, MediaType.APPLICATION_JSON));

	}

	@Override
	public void update(Computer o) {
		ComputerDTO comp = mapper.toDto(o);
		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		client.target(ROOT + COMPUTER + UPDATE).request()
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(comp, MediaType.APPLICATION_JSON));

	}

	@Override
	public void delete(long id) {

		Client client = ClientBuilder.newClient()
				.register(JacksonFeature.class);
		client.target(ROOT + COMPUTER + DELETE + "/" + id).request().delete();

	}

}
