package com.project.ElectronicStore.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ElectronicStore.Entities.Cart;
import com.project.ElectronicStore.Entities.User;

public interface CartRepository extends JpaRepository<Cart,String> {
	
	Optional<Cart> findByUser(User user);

}
