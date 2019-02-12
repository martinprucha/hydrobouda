package cz.mpr.hydrobouda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.mpr.hydrobouda.model.GuestbookMessage;

/**
 * Spring Data GuestbookMessage repository interface.
 * 
 * @author MPR
 * @version 1.0
 *
 */
@Repository
public interface GuestbookMessageRepository extends JpaRepository<GuestbookMessage, Long>{
	/**
	 * @param pageable
	 * @return a page with guestbook messages sorted by creation date in descending order
	 */
	public Page<GuestbookMessage> findAllByOrderByCreationDateDesc(Pageable pageable);
}
