package com.andrea.posty.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andrea.posty.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	
	
	Optional<User> findOneByEmail(String email);


}
