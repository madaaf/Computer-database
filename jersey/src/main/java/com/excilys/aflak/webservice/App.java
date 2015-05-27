package com.excilys.aflak.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

@RestController
@RequestMapping("rest/computer")
public class App {

	@GET
	@Produces("text/xml")
	@RequestMapping("test")
	public String getIt() {
		return "Got it!";
	}
}
