package com.project.ElectronicStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ElectronicStore.Entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{
}
