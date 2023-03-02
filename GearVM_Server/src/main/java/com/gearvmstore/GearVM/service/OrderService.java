package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.*;
import com.gearvmstore.GearVM.model.dto.order.OrderItemDto;
import com.gearvmstore.GearVM.model.dto.order.PlaceOrderDTO;
import com.gearvmstore.GearVM.repository.OrderItemRepository;
import com.gearvmstore.GearVM.repository.OrderRepository;
import com.gearvmstore.GearVM.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final JwtUtil jwtUtil;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, CustomerService customerService, ProductService productService, JwtUtil jwtUtil, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
        this.productService = productService;
        this.jwtUtil = jwtUtil;
        this.orderItemRepository = orderItemRepository;
    }

    public Boolean placeNewOrder(PlaceOrderDTO placeOrderDTO, String token) {
        try {
            Customer customer = customerService.getCustomer(Long.parseLong(jwtUtil.getIdFromToken(token)));
            if (customer == null)
                return false;

            Order order = new Order();
            order.setCustomerId(customer);
            order.setCreatedDate(LocalDateTime.now());
            order.setTotalPrice(placeOrderDTO.getTotalPrice());
            order.setOrderStatus(OrderStatus.PENDING);
            orderRepository.save(order);

            List<OrderItemDto> orderItems = placeOrderDTO.getOrderItems();
            for (OrderItemDto orderItemDto : orderItems) {
                OrderItem item = new OrderItem();
                Product product = productService.getProduct(orderItemDto.getProductId());
                item.setProductId(product);
                item.setOrderId(order);
                item.setQuantity(orderItemDto.getQuantity());
                item.setPrice(orderItemDto.getPrice());
                orderItemRepository.save(item);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Order> listOrders(Long customerId) {
        return orderRepository.findAllByCustomerIdOrderByCreatedDateDesc(customerId);
    }

    // Bugged
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
