package cz.mpr.hydrobouda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.mpr.hydrobouda.model.Message;
import cz.mpr.hydrobouda.repository.MessageRepository;

/**
 * Service containing message related operations.
 * @author MPR
 * @version 1.0
 * @since 20.9.2018
 *
 */
@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> findAllMessages() {
		return messageRepository.findAllByOrderByCreationDateDesc();
	}
	
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}
}
