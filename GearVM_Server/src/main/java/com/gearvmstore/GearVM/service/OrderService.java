package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.*;
import com.gearvmstore.GearVM.model.dto.order.OrderItemDto;
import com.gearvmstore.GearVM.model.dto.order.PlaceOrderDto;
import com.gearvmstore.GearVM.model.dto.order.UpdateOrderItem;
import com.gearvmstore.GearVM.model.dto.order.UpdateOrderStatusAndEmployee;
import com.gearvmstore.GearVM.model.response.GetOrderListResponse;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;
import com.gearvmstore.GearVM.model.response.GetPendingDirectOrderListResponse;
import com.gearvmstore.GearVM.repository.OrderItemRepository;
import com.gearvmstore.GearVM.repository.OrderRepository;
import com.gearvmstore.GearVM.repository.PaymentRepository;
import com.gearvmstore.GearVM.repository.ShippingDetailRepository;
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
    private final CustomerService customerService;
    private final ProductService productService;
    private final EmployeeService employeeService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final EntityManager em;
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    private final ShippingDetailRepository shippingDetailRepository;
    private final ShippingDetailService shippingDetailService;


    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductService productService, EmployeeService employeeService, JwtUtil jwtUtil, OrderItemRepository orderItemRepository, ModelMapper modelMapper, EntityManager em, PaymentService paymentService, PaymentRepository paymentRepository1, ShippingDetailRepository shippingDetailRepository1, ShippingDetailService shippingDetailService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.employeeService = employeeService;
        this.jwtUtil = jwtUtil;
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
        this.em = em;
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository1;
        this.shippingDetailRepository = shippingDetailRepository1;
        this.shippingDetailService = shippingDetailService;
    }

    public void updateOrderAfterPaymentInvoiceSucceeded(Long orderId, String paymentDescription, String customerName, String address, String email, String phoneNumber) {
        Order order = orderRepository.findById(orderId).get();

        Payment payment = paymentService.updateOnlinePayment(order.getPayment(), paymentDescription);
        ShippingDetail shippingDetail = shippingDetailService.updateOnlineShippingDetail(order.getShippingDetail(), customerName, address, email, phoneNumber);

        order.setPayment(payment);
        order.setShippingDetail(shippingDetail);
        order.setOrderStatus(OrderStatus.PAYMENT_DONE);
        orderRepository.save(order);
    }

    @Transactional
    public GetOrderResponse placeNewOrder(PlaceOrderDto placeOrderDto, String token) {
        try {
            Customer customer = customerService.getCustomer(Long.parseLong(jwtUtil.getIdFromToken(token)));
            if (customer == null)
                return null;

            Order order = new Order();

            order.setCustomer(customer);
            order.setCreatedDate(LocalDateTime.now());
            order.setUpdatedDate(LocalDateTime.now());
            order.setTotalPrice(placeOrderDto.getTotalPrice());
            order.setOrderStatus(OrderStatus.PAYMENT_PENDING);

            Payment payment = new Payment();
            paymentRepository.save(payment);

            ShippingDetail shippingDetail = new ShippingDetail();
            shippingDetail.setPhoneNumber(customer.getPhoneNumber());
            shippingDetailRepository.save(shippingDetail);

            order.setPayment(payment);
            order.setShippingDetail(shippingDetail);

            Order savedOrder = orderRepository.save(order);

            List<OrderItemDto> orderItemDtos = placeOrderDto.getOrderItemDtos();
            for (OrderItemDto orderItemDto : orderItemDtos) {
                OrderItem item = new OrderItem();
                Product product = productService.getProduct(orderItemDto.getProductId());
                item.setProduct(product);
                item.setOrder(order);
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

        for (Order order : orderList) {
            GetOrderListResponse orderListResponse = modelMapper.map(order, GetOrderListResponse.class);
            getOrderListResponseList.add(orderListResponse);
        }
        return getOrderListResponseList;
    }

    public List<GetPendingDirectOrderListResponse> getDirectPendingOrderList() {
        List<Order> orderList = orderRepository.findByOrderStatus(OrderStatus.DIRECT_PENDING);
        List<GetPendingDirectOrderListResponse> getOrderListResponseList = new ArrayList<>();

        for (Order order : orderList) {
            GetPendingDirectOrderListResponse orderResponse = new GetPendingDirectOrderListResponse();
            orderResponse.setId(order.getId());
            orderResponse.setCustomerName(order.getCustomer().getName());
            orderResponse.setCustomerPhoneNumber(order.getCustomer().getPhoneNumber());
            getOrderListResponseList.add(orderResponse);
        }
        return getOrderListResponseList;
    }

    public GetOrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return modelMapper.map(order, GetOrderResponse.class);
    }

    public GetOrderResponse getDirectPendingOrder(String customerName, String customerPhoneNumber) {
        Order order = orderRepository.findOrderByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(
                OrderStatus.DIRECT_PENDING,
                customerName, customerPhoneNumber);
        return modelMapper.map(order, GetOrderResponse.class);
    }

    public void addPaymentLinkToOrder(String paymentLink, Long orderId) {
        Order order = orderRepository.findById(orderId).get();

        Payment payment = order.getPayment();
        payment.setPaymentLink(paymentLink);
        paymentRepository.save(payment);

        orderRepository.save(order);
    }

    public GetOrderResponse updateOrderStatusAndEmployee(Long orderId, UpdateOrderStatusAndEmployee updateOrderStatusAndEmployee) {
        Order order = orderRepository.findById(orderId).get();
        // Temp orderStatus to compare
        OrderStatus oldOrderStatus = order.getOrderStatus();

        Employee employee = employeeService.getEmployee(updateOrderStatusAndEmployee.getEmployee().getId());
        order.setEmployee(employee);
        order.setOrderStatus(updateOrderStatusAndEmployee.getOrderStatus());
        order.setUpdatedDate(LocalDateTime.now());

        // Reduce quantity if order is confirmed
        OrderStatus newOrderStatus = order.getOrderStatus();
        if (newOrderStatus == OrderStatus.SHIPPING)
            for (com.gearvmstore.GearVM.model.OrderItem orderItem : order.getOrderItems()) {
                productService.reduceQuantity(orderItem.getProduct(), orderItem.getQuantity());
            }

        // Add quantity if order is from confirmed to something else
        if ((oldOrderStatus == OrderStatus.SHIPPING || oldOrderStatus == OrderStatus.SHIP_SUCCESS)
                && (newOrderStatus != OrderStatus.SHIPPING || newOrderStatus != OrderStatus.SHIP_SUCCESS)) {
            for (com.gearvmstore.GearVM.model.OrderItem orderItem : order.getOrderItems()) {
                productService.addQuantity(orderItem.getProduct(), orderItem.getQuantity());
            }
        }

        Order orderDb = orderRepository.save(order);
        return modelMapper.map(orderDb, GetOrderResponse.class);
    }

    public Order createDirectOrder(Long customerId) {
        Customer customer = customerService.getCustomer(customerId);

        Order order = new Order();
        order.setDirect(true);
        order.setCustomer(customer);
        order.setCreatedDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.DIRECT_PENDING);

        if (orderRepository.existsByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(OrderStatus.DIRECT_PENDING,
                customer.getName(), customer.getPhoneNumber()))
            return null;

        return orderRepository.save(order);
    }

    public Order updateAddOrderItem(UpdateOrderItem updateOrderItem) {
        Order order = orderRepository.findOrderByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(OrderStatus.DIRECT_PENDING,
                updateOrderItem.getCustomerName(), updateOrderItem.getCustomerPhone());
        int quantity = updateOrderItem.getAmount();

        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            // If there are item in cart --> plus existing item
            if (orderItem.getProduct().getId().toString().equals(updateOrderItem.getProductId())) {
                int newQuantity = orderItem.getQuantity() + updateOrderItem.getAmount();

                orderItem.setQuantity(newQuantity);
                orderItem.setPrice(newQuantity * orderItem.getProduct().getPrice());
                productService.reduceQuantity(orderItem.getProduct(), quantity);
                orderItemRepository.save(orderItem);

                return order;
            }
        }

        OrderItem orderItemToAdd = new OrderItem();
        Product product = productService.getProduct(Long.parseLong(updateOrderItem.getProductId()));

        orderItemToAdd.setProduct(product);
        orderItemToAdd.setId(order.getId());
        orderItemToAdd.setQuantity(quantity);
        orderItemToAdd.setPrice(quantity * product.getPrice());
        orderItemToAdd.setOrder(order);

        productService.reduceQuantity(product, quantity);

        orderItemRepository.save(orderItemToAdd);
        return order;
    }

    public Order updateReduceOrderItem(UpdateOrderItem updateOrderItem) {
        Order order = orderRepository.findOrderByOrderStatusAndCustomer_NameAndCustomer_PhoneNumber(OrderStatus.DIRECT_PENDING,
                updateOrderItem.getCustomerName(), updateOrderItem.getCustomerPhone());
        int quantity = updateOrderItem.getAmount();

        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            // If there are item in cart --> minus existing item
            if (orderItem.getProduct().getId().toString().equals(updateOrderItem.getProductId())) {
                int newQuantity = orderItem.getQuantity() - updateOrderItem.getAmount();

                orderItem.setQuantity(newQuantity);
                orderItem.setPrice(newQuantity * orderItem.getProduct().getPrice());
                productService.addQuantity(orderItem.getProduct(), quantity);
                orderItemRepository.save(orderItem);

                return order;
            }
        }

        OrderItem orderItemToAdd = new OrderItem();
        Product product = productService.getProduct(Long.parseLong(updateOrderItem.getProductId()));

        orderItemToAdd.setProduct(product);
        orderItemToAdd.setId(order.getId());
        orderItemToAdd.setQuantity(quantity);
        orderItemToAdd.setPrice(quantity * product.getPrice());
        orderItemToAdd.setOrder(order);

        productService.addQuantity(product, quantity);

        orderItemRepository.save(orderItemToAdd);
        return order;
    }
}
