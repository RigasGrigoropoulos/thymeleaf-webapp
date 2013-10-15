package net.webapp.user.repository;

import java.util.List;

import net.webapp.user.Authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	List<Authority> findByUserId(Long userId);

}
