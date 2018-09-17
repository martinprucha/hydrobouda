package cz.mpr.hydrobouda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

	@GetMapping(value = { "/template"})
	public String home() {
		return "template";
	}

}
