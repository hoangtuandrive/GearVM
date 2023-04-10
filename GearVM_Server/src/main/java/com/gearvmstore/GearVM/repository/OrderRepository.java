package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Order;
import com.gearvmstore.GearVM.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerIdOrderByCreatedDateDesc(Long customerId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    boolean existsByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(OrderStatus orderStatus, String name, String phoneNumber);

    Order findOrderByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(OrderStatus orderStatus, String name, String phoneNumber);
}
