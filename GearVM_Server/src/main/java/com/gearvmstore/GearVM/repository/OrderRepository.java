package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Order;
import com.gearvmstore.GearVM.model.OrderStatus;
import com.gearvmstore.GearVM.model.dto.order.PrintOrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDateTime;
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


    @Query(nativeQuery = true)
    List<PrintOrderDto> findPrintOrderByOrderId_Named(@Param("orderId")Long orderId);


    List<Order> findDistinctByIsDirectAndOrderStatusNotAndIdEqualsOrCustomer_NameContainingIgnoreCaseOrCustomer_PhoneNumberContainingIgnoreCaseOrderByCreatedDateDesc
            (boolean isDirect, OrderStatus orderStatus, Long id, String customerName, String customerPhoneNumber);
}

