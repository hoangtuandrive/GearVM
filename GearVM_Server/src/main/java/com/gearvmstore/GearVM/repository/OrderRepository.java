package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerIdOrderByCreatedDateDesc(Long customerId);
}
