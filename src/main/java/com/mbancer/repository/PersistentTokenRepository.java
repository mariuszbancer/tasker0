package com.mbancer.repository;

import com.mbancer.domain.PersistentToken;
import com.mbancer.domain.User;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(User user);

    List<PersistentToken> findByTokenDateBefore(LocalDateTime LocalDateTime);

}
