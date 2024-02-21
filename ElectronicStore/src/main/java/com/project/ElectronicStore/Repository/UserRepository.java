package com.project.ElectronicStore.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ElectronicStore.Entities.Category;
import com.project.ElectronicStore.Entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	
	//custom finder methods
	Optional<User> findByEmail(String email);
	List<User> findByUserNameContaining(String keyword);

}
