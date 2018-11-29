package cz.mpr.hydrobouda.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private int pageNumber = 1; 
	private int pageSize = 5;
	
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
		
		retrieveAndAddGuestbookMessagePageToModel(model, 1, pageSize);
        
		return "guestbook";
	}	
	
	private void retrieveAndAddGuestbookMessagePageToModel(Model model, Integer pageNumber, Integer pageSize) {
		Page<GuestbookMessage> guestbookMessagePage = guestbookMessageService.findAllGuestbookMessagesPaginated(PageRequest.of(pageNumber - 1, pageSize));
		model.addAttribute("guestbookMessagePage", guestbookMessagePage);
        model.addAttribute("guestbookMessage", new GuestbookMessage());
        
        addPageNumbersToModel(guestbookMessagePage, model);
	}
	
	private <T> void addPageNumbersToModel(Page<T> page, Model model) {
		int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
	}
}
