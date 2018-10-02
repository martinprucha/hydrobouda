package cz.mpr.hydrobouda.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import cz.mpr.hydrobouda.model.Message;
import cz.mpr.hydrobouda.repository.MessageRepository;

/**
 * Test of MessageRepository methods.
 * 
 * @author MPR
 * @version 1.0
 * @since 20.9.2018
 *
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class MessageRepositoryTest {
	@Autowired
	private MessageRepository messageRepository;

	/**
	 * CRUD operations test.
	 */
	@Test
	public void testCRUDOperations() {
		// verify that there are no items
		List<Message> messages = messageRepository.findAll();
		assertEquals(0, messages.size());
		
		// create new message item
		Message message = new Message();
		message.setAuthor("Initial author");
		message.setCreationDate(new Date());
		message.setText("Initial message");
		Message createdMessage = messageRepository.save(message);
		
		// verify that there is just one item 
		messages = messageRepository.findAll();
		assertEquals(1, messages.size());

		// retrieve created message and verify its data
		Optional<Message> retrievedCreatedMessage = messageRepository.findById(createdMessage.getId());
		assertEquals(createdMessage, retrievedCreatedMessage.get());
		
		// update retrieved message item
		createdMessage.setAuthor("Updated author");
		createdMessage.setCreationDate(new Date());
		createdMessage.setText("Updated message");
		Message updatedMessage = messageRepository.save(createdMessage);
		
		// verify that there is just one item 
		messages = messageRepository.findAll();
		assertEquals(1, messages.size());
		
		// retrieve updated message item
		Optional<Message> retrievedUpdatedMessage = messageRepository.findById(updatedMessage.getId());
		assertEquals(updatedMessage, retrievedUpdatedMessage.get());
		
		// delete message item
		messageRepository.deleteById(retrievedUpdatedMessage.get().getId());
		
		// verify that there are no items
		messages = messageRepository.findAll();
		assertTrue(messages.isEmpty());
	}
	
}
