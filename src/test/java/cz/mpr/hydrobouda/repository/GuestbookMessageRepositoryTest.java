package cz.mpr.hydrobouda.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import cz.mpr.hydrobouda.model.GuestbookMessage;
import cz.mpr.hydrobouda.repository.GuestbookMessageRepository;

/**
 * Test of GuestbookMessageRepository methods.
 * 
 * @author MPR
 * @version 1.0
 * @since 20.9.2018
 *
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class GuestbookMessageRepositoryTest {
	@Autowired
	private GuestbookMessageRepository guestbookMessageRepository;

	/**
	 * CRUD operations test.
	 */
	@Test
	public void testCRUDOperations() {
		// clear all the entries in the database
		guestbookMessageRepository.deleteAll();

		// verify that there are no items
		List<GuestbookMessage> guestbookMessages = guestbookMessageRepository.findAll();
		assertEquals(0, guestbookMessages.size());
		
		// create new message item
		GuestbookMessage message = new GuestbookMessage();
		message.setAuthor("Initial author");
		message.setCreationDate(new Date());
		message.setText("Initial message");
		GuestbookMessage createdMessage = guestbookMessageRepository.save(message);
		
		// verify that there is just one item 
		guestbookMessages = guestbookMessageRepository.findAll();
		assertEquals(1, guestbookMessages.size());

		// retrieve created message and verify its data
		Optional<GuestbookMessage> retrievedCreatedMessage = guestbookMessageRepository.findById(createdMessage.getId());
		assertEquals(createdMessage, retrievedCreatedMessage.get());
		
		// update retrieved message item
		createdMessage.setAuthor("Updated author");
		createdMessage.setCreationDate(new Date());
		createdMessage.setText("Updated message");
		GuestbookMessage updatedMessage = guestbookMessageRepository.save(createdMessage);
		
		// verify that there is just one item 
		guestbookMessages = guestbookMessageRepository.findAll();
		assertEquals(1, guestbookMessages.size());
		
		// retrieve updated message item
		Optional<GuestbookMessage> retrievedUpdatedMessage = guestbookMessageRepository.findById(updatedMessage.getId());
		assertEquals(updatedMessage, retrievedUpdatedMessage.get());
		
		// delete message item
		guestbookMessageRepository.deleteById(retrievedUpdatedMessage.get().getId());
		
		// verify that there are no items
		guestbookMessages = guestbookMessageRepository.findAll();
		assertTrue(guestbookMessages.isEmpty());
	}
	
	/**
	 * Paged find test.
	 */
	@Test
	public void testPagedFind() {
		// clear all the guestbook messages in database
		guestbookMessageRepository.deleteAll();
		
		// create new message items
		for(int i = 0; i < 5; i++) {
			// create new message item
			GuestbookMessage message = new GuestbookMessage();
			message.setAuthor("Author " + i);
			message.setCreationDate(new Date());
			message.setText("Message " + i);
			guestbookMessageRepository.save(message);
		}
		
		// verify paged find of guestbook messages
		for(int i = 1; i <= 5; i++) {
			Page<GuestbookMessage> page = guestbookMessageRepository.findAllByOrderByCreationDateDesc(PageRequest.of(1, 1));
			assertNotNull(page.getContent());
			assertTrue(page.getContent().size() == 1);
		}
	}
}
