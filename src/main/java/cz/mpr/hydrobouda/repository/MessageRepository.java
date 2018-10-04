package cz.mpr.hydrobouda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.mpr.hydrobouda.model.Message;

/**
 * Spring Data Message repository interface.
 * 
 * @author MPR
 * @version 1.0
 * @since 20.9.2018
 *
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	/**
	 * @return a collection of guestbook messages sorted by creation date in descending order
	 */
	public List<Message> findAllByOrderByCreationDateDesc();
}
