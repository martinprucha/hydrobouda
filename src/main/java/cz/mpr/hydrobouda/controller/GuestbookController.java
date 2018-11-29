package cz.mpr.hydrobouda.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cz.mpr.hydrobouda.model.GuestbookMessage;
import cz.mpr.hydrobouda.service.GuestbookMessageService;

/**
 * Controller for guestbook.
 * 
 * @author MPR
 * @version 1.0
 * @since 1.10.2018
 *
 */
@Controller
public class GuestbookController {
	@Autowired
	GuestbookMessageService guestbookMessageService;
	
	@GetMapping("/guestbook")
	public String guestbook(Model model) {
		// read existing guestbook messages and add them to model
		model.addAttribute("guestbookMessages", guestbookMessageService.findAllGuestbookMessages());
		
		// add a new  backing object for the form
		model.addAttribute("guestbookMessage", new GuestbookMessage());
		
		return "guestbook";
	}

	@PostMapping("/guestbook")
	public String saveGuestbookMessage(GuestbookMessage guestbookMessage, Model model) {
		// save new guestbook guestbook message
		guestbookMessage.setCreationDate(new Date());
		guestbookMessageService.saveGuestbookMessage(guestbookMessage);
		
		// read existing guestbook messages and add them to model
		model.addAttribute("guestbookMessages", guestbookMessageService.findAllGuestbookMessages());
		
		return "guestbook";
	}
}
