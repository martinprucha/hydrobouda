package cz.mpr.hydrobouda.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.mpr.hydrobouda.model.Message;
import cz.mpr.hydrobouda.service.MessageService;

@RestController
@RequestMapping("/rest/guestbook")
public class GuestbookRestController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public List<Message> guestbook() {
        return messageService.findAllMessages();
    }

    @PostMapping
    public Message saveGuestbookMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

}