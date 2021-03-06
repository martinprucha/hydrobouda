package cz.mpr.hydrobouda.mvc.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.mpr.hydrobouda.helper.MVCControllerHelper;
import cz.mpr.hydrobouda.jpa.model.GuestbookMessage;
import cz.mpr.hydrobouda.service.IGuestbookMessageService;

/**
 * MVC controller for guestbook feature.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@Controller
public class GuestbookMVCController {
	@Autowired
	IGuestbookMessageService guestbookMessageService;
	
	private int pageNumber = 0; 
	private int pageSize = 10;
	
	@GetMapping("/guestbook")
	public String guestbook(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		page.ifPresent(p -> pageNumber = p);
		size.ifPresent(s -> pageSize = s);

		retrieveAndAddGuestbookMessagePageToModel(model, pageNumber, pageSize);
        
		return "guestbook";
	}
	
	
	@PostMapping("/guestbook")
	public String saveGuestbookMessage(GuestbookMessage guestbookMessage, Model model) {
		guestbookMessage.setCreationDate(new Date());
		guestbookMessageService.saveGuestbookMessage(guestbookMessage);
		
		retrieveAndAddGuestbookMessagePageToModel(model, 0, pageSize);
        
		return "guestbook";
	}	
	
	private void retrieveAndAddGuestbookMessagePageToModel(Model model, Integer pageNumber, Integer pageSize) {
		Page<GuestbookMessage> guestbookMessagePage = guestbookMessageService.findAllGuestbookMessagesPaginated(PageRequest.of(pageNumber, pageSize));
		model.addAttribute("guestbookMessagePage", guestbookMessagePage);
        model.addAttribute("guestbookMessage", new GuestbookMessage());
        
        MVCControllerHelper.addPageNumbersToModel(guestbookMessagePage, "pageNumbers", model);
	}
}
