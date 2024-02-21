package com.project.ElectronicStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ElectronicStore.Entities.Cart;
import com.project.ElectronicStore.Entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
