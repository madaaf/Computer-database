package com.excilys.aflak.webservice;

import java.util.List;
import java.util.Locale;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.aflak.dto.ComputerDTO;
import com.excilys.aflak.mapper.ComputerMapper;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.validator.Date.Pattern;

@RestController
@RequestMapping("rest/computer")
public class RestComputerService implements IRestService<ComputerDTO> {

	@Autowired
	private ComputerService service;

	Locale locale = LocaleContextHolder.getLocale();
	Pattern language = Pattern.map(locale.getLanguage());

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findAll")
	@Override
	public List<ComputerDTO> findAll() {
		List<Computer> computers = service.getAllComputers();
		ComputerMapper mapper = new ComputerMapper(language);
		List<ComputerDTO> computersDTO = mapper.toListDto(computers);
		return computersDTO;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("find/{id}")
	@Override
	public ComputerDTO find(@PathVariable("id") long id) {
		Computer computer = service.findComputer(id);
		ComputerMapper mapper = new ComputerMapper(language);
		if (computer == null)
			return null;
		return mapper.toDto(computer);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findSome/{filtre}/{colomn}/{way}/{debut}/{limit}")
	public List<Computer> someComputer(@PathVariable("filtre") String filtre,
			@PathVariable("colomn") String colomn,
			@PathVariable("way") String way, @PathVariable("debut") int debut,
			@PathVariable("limit") int limit) {
		return service
				.getSomeFiltredComputer(filtre, colomn, way, debut, limit);
	}

	/* colomn, way, debut, limit */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findSome/{colomn}/{way}/{debut}/{limit}")
	public List<Computer> someComputer2(@PathVariable("colomn") String colomn,
			@PathVariable("way") String way, @PathVariable("debut") int debut,
			@PathVariable("limit") int limit) {

		return service.getSomeFiltredComputer(null, colomn, way, debut, limit);
	}

	/* filtre, debut, fin */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findSome/{filtre}/{debut}/{limit}")
	public List<Computer> someComputer3(@PathVariable("filtre") String filtre,
			@PathVariable("debut") int debut, @PathVariable("limit") int limit) {
		return service.getSomeFiltredComputer(filtre, "computer.id", "ASC",
				debut, limit);
	}

	/* debut, limit */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("findSome/{debut}/{limit}")
	public List<Computer> someComputer4(@PathVariable("debut") int debut,
			@PathVariable("limit") int limit) {
		return service.getSomeFiltredComputer(null, "computer.id", "ASC",
				debut, limit);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@RequestMapping("create")
	public void create2(@FormParam("name") String name,
			@FormParam("introduced") String introduced,
			@FormParam("discontinued") String discontinued,
			@FormParam("companyName") String companyName,
			@FormParam("companyId") int companyId) {

		// service.createComputer(computer);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping("create")
	public void create3(@FormParam("name") String name,
			@FormParam("introduced") String introduced,
			@FormParam("discontinued") String discontinued,
			@FormParam("companyName") String companyName,
			@FormParam("companyId") int companyId) {

		// service.createComputer(computer);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(ComputerDTO obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ComputerDTO obj) {
		// TODO Auto-generated method stub

	}

}
