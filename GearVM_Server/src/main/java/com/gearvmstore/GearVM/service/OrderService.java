package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.*;
import com.gearvmstore.GearVM.model.dto.order.OrderItemDto;
import com.gearvmstore.GearVM.model.dto.order.PlaceOrderDto;
import com.gearvmstore.GearVM.model.response.GetOrderListResponse;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;
import com.gearvmstore.GearVM.repository.OrderItemRepository;
import com.gearvmstore.GearVM.repository.OrderRepository;
import com.gearvmstore.GearVM.utility.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final EntityManager em;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, CustomerService customerService, ProductService productService, JwtUtil jwtUtil, OrderItemRepository orderItemRepository, ModelMapper modelMapper, EntityManager em) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
        this.productService = productService;
        this.jwtUtil = jwtUtil;
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
        this.em = em;
    }

    public void updateOrderAfterPaymentInvoiceSucceeded(Long orderId, String paymentId) {
        Order order = orderRepository.findById(orderId).get();
        order.setPaymentId(paymentId);
        order.setOrderStatus(OrderStatus.PAYMENT_DONE);
        orderRepository.save(order);
    }

    @Transactional
    public GetOrderResponse placeNewOrder(PlaceOrderDto placeOrderDTO, String token) {
        try {
            Customer customer = customerService.getCustomer(Long.parseLong(jwtUtil.getIdFromToken(token)));
            if (customer == null)
                return null;

            Order order = new Order();
            order.setCustomerId(customer);
            order.setCreatedDate(LocalDateTime.now());
            order.setTotalPrice(placeOrderDTO.getTotalPrice());
            order.setOrderStatus(OrderStatus.PAYMENT_PENDING);
            Order savedOrder = orderRepository.save(order);

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

            em.refresh(savedOrder);
            return getOrder(savedOrder.getId());
        } catch (Exception e) {
            return null;
        }
    }

    public List<Order> listOrdersByCustomerId(Long customerId) {
        return orderRepository.findAllByCustomerIdOrderByCreatedDateDesc(customerId);
    }

    public List<GetOrderListResponse> getOrders() {
        List<Order> orderList = orderRepository.findAll();
        List<GetOrderListResponse> getOrderListResponseList = new ArrayList<>();
//        List<GetOrderListResponse> getOrderListResponseList = Arrays.asList(modelMapper.map(orderList, GetOrderListResponse[].class));
        for (Order order : orderList) {
            GetOrderListResponse orderListResponse = modelMapper.map(order, GetOrderListResponse.class);
            getOrderListResponseList.add(orderListResponse);
        }
        return getOrderListResponseList;
    }

    public GetOrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return modelMapper.map(order, GetOrderResponse.class);
    }
}
