package net.webapp.user.repository;

import net.webapp.user.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	

}
