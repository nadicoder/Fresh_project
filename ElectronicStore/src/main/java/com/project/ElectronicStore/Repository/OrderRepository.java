package com.project.ElectronicStore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ElectronicStore.Entities.Order;
import com.project.ElectronicStore.Entities.User;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);
}