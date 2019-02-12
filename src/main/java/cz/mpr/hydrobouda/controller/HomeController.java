package cz.mpr.hydrobouda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MVC controller for home.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@Controller
public class HomeController {

	@GetMapping(value = { "/", "/home" })
	public String home() {
		return "home";
	}

}
