package cz.mpr.hydrobouda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.mpr.hydrobouda.model.GuestbookMessage;

/**
 * Spring Data GuestbookMessage repository interface.
 * 
 * @author MPR
 * @version 1.0
 * @since 20.9.2018
 *
 */
@Repository
public interface GuestbookMessageRepository extends JpaRepository<GuestbookMessage, Long>{
	/**
	 * @return a collection of guestbook messages sorted by creation date in descending order
	 */
	public List<GuestbookMessage> findAllByOrderByCreationDateDesc();
}
