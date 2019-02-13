package cz.mpr.hydrobouda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cz.mpr.hydrobouda.jpa.model.GuestbookMessage;

public interface IGuestbookMessageService {

	Page<GuestbookMessage> findAllGuestbookMessagesPaginated(Pageable pageable);

	GuestbookMessage saveGuestbookMessage(GuestbookMessage message);

	void deleteGuestbookMessage(Long id);

}