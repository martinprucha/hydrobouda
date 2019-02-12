package cz.mpr.hydrobouda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MVC controller for template.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@Controller
public class TemplateController {

	@GetMapping(value = { "/template"})
	public String home() {
		return "template";
	}

}
