package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerIdOrderByCreatedDateDesc(Long customerId);
}
