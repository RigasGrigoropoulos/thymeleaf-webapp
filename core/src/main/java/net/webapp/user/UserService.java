package net.webapp.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	User getUserById(Long userId);

	Page<User> getAllUsers(Pageable pageable);

	User saveUser(User user);

	void deleteUser(Long userId);

	List<Authority> getAuthoritiesForUser(Long id);

	Authority saveAuthority(Authority authority);

	void deleteAuthority(Long id);

}
