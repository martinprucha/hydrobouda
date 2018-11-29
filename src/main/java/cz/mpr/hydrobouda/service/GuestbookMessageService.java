package cz.mpr.hydrobouda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cz.mpr.hydrobouda.model.GuestbookMessage;
import cz.mpr.hydrobouda.repository.GuestbookMessageRepository;

/**
 * Service containing guestbook message related operations.
 * @author MPR
 * @version 1.0
 * @since 20.9.2018
 *
 */
@Service
public class GuestbookMessageService {

	@Autowired
	private GuestbookMessageRepository guestbookMessageRepository;
	
	public Page<GuestbookMessage> findAllGuestbookMessagesPaginated(Pageable pageable) {
		return guestbookMessageRepository.findAllByOrderByCreationDateDesc(pageable);
	}
	
	public GuestbookMessage saveGuestbookMessage(GuestbookMessage message) {
		return guestbookMessageRepository.save(message);
	}
	
	public void deleteGuestbookMessage(Long id) {
		guestbookMessageRepository.deleteById(id);
	}
}
