package net.webapp.user.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.webapp.user.Authority;
import net.webapp.user.User;
import net.webapp.user.UserService;
import net.webapp.user.repository.AuthorityRepository;
import net.webapp.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@PostConstruct
	public void init() {
		LOG.info(getClass().getSimpleName() + ".start()");
	}
	
	@PreDestroy
	public void destroy() {
		LOG.info(getClass().getSimpleName() + ".destroy()");
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authRepository;
	

	@Override
	public User getUserById(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(Long userId) {
		userRepository.delete(userId);
	}
	
	@Override
	public List<Authority> getAuthoritiesForUser(Long id) {
		return authRepository.findByUserId(id);
	}
	
	@Override
	public Authority saveAuthority(Authority authority) {
		return authRepository.save(authority);
	}
	
	@Override
	public void deleteAuthority(Long id) {
		authRepository.delete(id);
	}
}
