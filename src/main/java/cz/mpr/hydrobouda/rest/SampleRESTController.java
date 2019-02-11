package cz.mpr.hydrobouda.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleRESTController {
	
	@GetMapping("/now")
	public LocalDateTime now() {
		return LocalDateTime.now();
	}
}
