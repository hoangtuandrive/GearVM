package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Order;
import com.gearvmstore.GearVM.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerIdAndIsDirectOrderByUpdatedDateDesc(Long customerId, boolean isDirect);

    List<Order> findAllByOrderStatusOrderByCreatedDateDesc(OrderStatus orderStatus);

    boolean existsByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(OrderStatus orderStatus, String name, String phoneNumber);

    Order findOrderByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(OrderStatus orderStatus, String name, String phoneNumber);

    List<Order> findAllByIsDirectOrderByCreatedDateDesc(boolean isDirect);

    List<Order> findDistinctByIsDirectAndIdEqualsOrCustomer_NameContainingIgnoreCaseOrCustomer_PhoneNumberContainingIgnoreCaseOrderByCreatedDateDesc
            (boolean isDirect, Long id, String customerName, String customerPhoneNumber);

    List<Order> findAllByIsDirectAndOrderStatusNotOrderByCreatedDateDesc(boolean isDirect, OrderStatus orderStatus);

    List<Order> findDistinctByIsDirectAndOrderStatusNotAndIdEqualsOrCustomer_NameContainingIgnoreCaseOrCustomer_PhoneNumberContainingIgnoreCaseOrderByCreatedDateDesc
            (boolean isDirect, OrderStatus orderStatus, Long id, String customerName, String customerPhoneNumber);
}
