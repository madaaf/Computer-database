package com.excilys.aflak.webservice.impl;

import java.util.List;
import java.util.Locale;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.aflak.dao.ICrudDAO.Sort;
import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.dto.PageRequest;
import com.excilys.aflak.mapper.ComputerMapper;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.validator.Date.Pattern;
import com.excilys.aflak.webservice.IRestService;

@RestController
@RequestMapping("rest/computer")
public class RestComputerService implements IRestService<ComputerDTO> {

	@Autowired
	private ComputerService service;

	Locale locale = LocaleContextHolder.getLocale();
	Pattern language = Pattern.map(locale.getLanguage());
	ComputerMapper mapper = new ComputerMapper(language);

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping("list")
	@Override
	public List<ComputerDTO> findAll() {
		List<Computer> computers = service.list();
		List<ComputerDTO> computersDTO = mapper.toListDto(computers);
		return computersDTO;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping("/{id}")
	@Override
	public ComputerDTO find(@PathVariable("id") long id) {
		Computer computer = service.find(id);
		ComputerMapper mapper = new ComputerMapper(language);
		if (computer == null)
			return null;
		return mapper.toDto(computer);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping("list/{filtre}/{colomn}/{way}/{debut}/{limit}")
	public List<Computer> someComputer(@PathVariable("filtre") String filtre,
			@PathVariable("colomn") String colomn,
			@PathVariable("way") String way, @PathVariable("debut") int debut,
			@PathVariable("limit") int limit) {
		PageRequest request = PageRequest.Builder.builder().filter(filtre)
				.field(colomn).sort(Sort.valueOf(way)).pageSize(limit)
				.pageNumber(debut).build();
		return service.list(request);
	}

	/* colomn, way, debut, limit */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping("list/{colomn}/{way}/{debut}/{limit}")
	public List<Computer> someComputer2(@PathVariable("colomn") String colomn,
			@PathVariable("way") String way, @PathVariable("debut") int debut,
			@PathVariable("limit") int limit) {

		PageRequest request = PageRequest.Builder.builder().field(colomn)
				.sort(Sort.valueOf(way)).pageSize(limit).pageNumber(debut)
				.build();

		return service.list(request);
	}

	/* filtre, debut, fin */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping("list/{filtre}/{debut}/{limit}")
	public List<Computer> someComputer3(@PathVariable("filtre") String filtre,
			@PathVariable("debut") int debut, @PathVariable("limit") int limit) {
		PageRequest request = PageRequest.Builder.builder().filter(filtre)
				.pageSize(limit).pageNumber(debut).build();
		return service.list(request);
	}

	@DELETE
	@RequestMapping("delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public void delete(@PathVariable("id") long id) {
		service.delete(id);
	}

	@POST
	@RequestMapping("create")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public void create(ComputerDTO obj) {
		service.create(mapper.fromDto(obj));

	}

	@PUT
	@RequestMapping("update")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public void update(ComputerDTO obj) {
		service.update(mapper.fromDto(obj));
	}

}
