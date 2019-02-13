package cz.mpr.hydrobouda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cz.mpr.hydrobouda.jpa.model.GuestbookMessage;
import cz.mpr.hydrobouda.jpa.repository.GuestbookMessageRepository;

// TODO - MPR - define interface for this service
/**
 * Service containing guestbook message related operations.
 * @author MPR
 * @version 1.0
 * @since 20.9.2018
 *
 */
@Service
public class GuestbookMessageService implements IGuestbookMessageService {

	@Autowired
	private GuestbookMessageRepository guestbookMessageRepository;
	
	/* (non-Javadoc)
	 * @see cz.mpr.hydrobouda.service.IGuestbookMessageService#findAllGuestbookMessagesPaginated(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<GuestbookMessage> findAllGuestbookMessagesPaginated(Pageable pageable) {
		return guestbookMessageRepository.findAllByOrderByCreationDateDesc(pageable);
	}
	
	/* (non-Javadoc)
	 * @see cz.mpr.hydrobouda.service.IGuestbookMessageService#saveGuestbookMessage(cz.mpr.hydrobouda.jpa.model.GuestbookMessage)
	 */
	@Override
	public GuestbookMessage saveGuestbookMessage(GuestbookMessage message) {
		return guestbookMessageRepository.save(message);
	}
	
	/* (non-Javadoc)
	 * @see cz.mpr.hydrobouda.service.IGuestbookMessageService#deleteGuestbookMessage(java.lang.Long)
	 */
	@Override
	public void deleteGuestbookMessage(Long id) {
		guestbookMessageRepository.deleteById(id);
	}
}
