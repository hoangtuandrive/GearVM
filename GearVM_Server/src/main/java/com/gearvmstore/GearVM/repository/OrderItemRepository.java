package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
