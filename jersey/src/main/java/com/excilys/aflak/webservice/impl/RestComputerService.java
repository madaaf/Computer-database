package com.excilys.aflak.webservice.impl;

import java.util.List;
import java.util.Locale;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/computers")
public class RestComputerService implements IRestService<ComputerDTO> {

	@Autowired
	private ComputerService service;

	Locale locale = LocaleContextHolder.getLocale();
	Pattern language = Pattern.map(locale.getLanguage());
	ComputerMapper mapper = new ComputerMapper(language);

	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(value = "", method = RequestMethod.GET)
	@Override
	public List<ComputerDTO> findAll() {
		List<Computer> computers = service.list();
		List<ComputerDTO> computersDTO = mapper.toListDto(computers);
		return computersDTO;
	}

	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Override
	public ComputerDTO find(@PathVariable("id") long id) {
		Computer computer = service.find(id);
		ComputerMapper mapper = new ComputerMapper(language);
		if (computer == null)
			return null;
		return mapper.toDto(computer);
	}
	


	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(value = "/{filtre}/{colomn}/{way}/{debut}/{limit}", method = RequestMethod.GET)
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
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(value = "/{colomn}/{way}/{debut}/{limit}", method = RequestMethod.GET)
	public List<Computer> someComputer2(@PathVariable("colomn") String colomn,
			@PathVariable("way") String way, @PathVariable("debut") int debut,
			@PathVariable("limit") int limit) {

		PageRequest request = PageRequest.Builder.builder().field(colomn)
				.sort(Sort.valueOf(way)).pageSize(limit).pageNumber(debut)
				.build();

		return service.list(request);
	}

	/* filtre, debut, fin */
	@Produces({ MediaType.APPLICATION_JSON })
	@RequestMapping(value = "/{filtre}/{debut}/{limit}", method = RequestMethod.GET)
	public List<Computer> someComputer3(@PathVariable("filtre") String filtre,
			@PathVariable("debut") int debut, @PathVariable("limit") int limit) {
		PageRequest request = PageRequest.Builder.builder().filter(filtre)
				.pageSize(limit).pageNumber(debut).build();
		return service.list(request);
	}

	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public void delete(@PathVariable("id") long id) {
		service.delete(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public void create(ComputerDTO obj) {
		service.create(mapper.fromDto(obj));

	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public void update(ComputerDTO obj) {
		service.update(mapper.fromDto(obj));
	}

}
