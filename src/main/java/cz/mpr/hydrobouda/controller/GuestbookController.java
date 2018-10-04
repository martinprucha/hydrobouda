package cz.mpr.hydrobouda.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cz.mpr.hydrobouda.model.Message;
import cz.mpr.hydrobouda.service.MessageService;

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
	MessageService messageService;
	
	@GetMapping("/guestbook")
	public String guestbook(Model model) {
		// read existing messages and add them to model
		model.addAttribute("messages", messageService.findAllMessages());
		
		// add a new message backing object for the form
		model.addAttribute("message", new Message());
		
		return "guestbook";
	}

	@PostMapping("/guestbook")
	public String saveGuestbookMessage(Message message, Model model) {
		// save new message
		message.setCreationDate(new Date());
		messageService.saveMessage(message);
		
		// read existing messages and add them to model
		model.addAttribute("messages", messageService.findAllMessages());
		
		return "guestbook";
	}
}
