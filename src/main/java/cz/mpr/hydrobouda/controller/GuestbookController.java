package cz.mpr.hydrobouda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestbookController {
	
	@GetMapping("/guestbook")
	public String guestbook() {
		return "guestbook";
	}
}
