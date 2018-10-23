package cz.mpr.hydrobouda.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.mpr.hydrobouda.model.Message;
import cz.mpr.hydrobouda.service.MessageService;

// TODO - MPR - #29 - define REST CRUD operations for message
@RestController
@RequestMapping("/api/messages")
public class GuestbookRestController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public List<Message> findAllMessages() {
        return messageService.findAllMessages();
    }
    
    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
    	return messageService.saveMessage(message);
    }
    
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
    	messageService.deleteMessage(id);
    }
}