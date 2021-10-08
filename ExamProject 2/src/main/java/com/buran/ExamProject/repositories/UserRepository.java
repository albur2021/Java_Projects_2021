package com.buran.ExamProject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.buran.ExamProject.models.User;



public interface UserRepository extends CrudRepository<User, Long>  {
	 Optional<User> findByEmail(String email);
}
