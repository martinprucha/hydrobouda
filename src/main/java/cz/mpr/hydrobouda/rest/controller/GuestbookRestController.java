package cz.mpr.hydrobouda.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.mpr.hydrobouda.model.Message;
import cz.mpr.hydrobouda.rest.response.BaseResponse;
import cz.mpr.hydrobouda.rest.response.GuestbookMessageResponse;
import cz.mpr.hydrobouda.rest.response.GuestbookMessagesResponse;
import cz.mpr.hydrobouda.service.MessageService;

// TODO - MPR - #29 - define REST CRUD operations for message
@RestController
@RequestMapping("/api/messages")
public class GuestbookRestController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public GuestbookMessagesResponse findAllMessages() {
    	GuestbookMessagesResponse response = new GuestbookMessagesResponse();
    	response.setMessages(messageService.findAllMessages());
        response.setHttpStatusCode(HttpStatus.OK.value());
        return response;
    }
    
    @PostMapping
    public GuestbookMessageResponse saveMessage(@RequestBody Message message) {
    	GuestbookMessageResponse response = new GuestbookMessageResponse();
    	response.setMessage(messageService.saveMessage(message));
    	response.setHttpStatusCode(HttpStatus.OK.value());
    	return response;
    }
    
    @DeleteMapping("/{id}")
    public BaseResponse deleteMessage(@PathVariable Long id) {
    	messageService.deleteMessage(id);
    	BaseResponse response = new BaseResponse();
    	response.setHttpStatusCode(HttpStatus.OK.value());
    	return response;
    }
}