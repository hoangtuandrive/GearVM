package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.*;
import com.gearvmstore.GearVM.model.dto.order.OrderItemDto;
import com.gearvmstore.GearVM.model.dto.order.PlaceOrderDto;
import com.gearvmstore.GearVM.model.dto.order.UpdateOrderStatusAndEmployee;
import com.gearvmstore.GearVM.model.response.GetOrderListResponse;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;
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
    private final OrderItemService orderItemService;
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
    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, CustomerService customerService, ProductService productService, EmployeeService employeeService, JwtUtil jwtUtil, OrderItemRepository orderItemRepository, ModelMapper modelMapper, EntityManager em, PaymentService paymentService, PaymentRepository paymentRepository, ShippingDetailRepository shippingDetailRepository, PaymentRepository paymentRepository1, ShippingDetailRepository shippingDetailRepository1, ShippingDetailService shippingDetailService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
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

    public void updateOrderAfterPaymentInvoiceSucceeded(Long orderId, String paymentDescription, String customerName, String address, String email) {
        Order order = orderRepository.findById(orderId).get();

        Payment payment = paymentService.updateOnlinePayment(order.getPayment(), paymentDescription);
        ShippingDetail shippingDetail = shippingDetailService.updateOnlineShippingDetail(order.getShippingDetail(), customerName, address, email);

        order.setPayment(payment);
        order.setShippingDetail(shippingDetail);
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
            LocalDateTime localDateTime = LocalDateTime.now();

            order.setCustomer(customer);
            order.setCreatedDate(localDateTime);
            order.setUpdatedDate(localDateTime);
            order.setTotalPrice(placeOrderDTO.getTotalPrice());
            order.setOrderStatus(OrderStatus.PAYMENT_PENDING);

            Payment payment = new Payment();
            paymentRepository.save(payment);

            ShippingDetail shippingDetail = new ShippingDetail();
            // TODO set phone number trước khi tạo link
//            shippingDetail.setPhoneNumber(placeOrderDTO.getPhoneNumber());
            shippingDetail.setPhoneNumber("029471420");
            shippingDetailRepository.save(shippingDetail);

            order.setPayment(payment);
            order.setShippingDetail(shippingDetail);

            Order savedOrder = orderRepository.save(order);

            List<OrderItemDto> orderItems = placeOrderDTO.getOrderItems();
            for (OrderItemDto orderItemDto : orderItems) {
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

    //TODO: test
    public Order addPaymentLinkToOrder(String paymentLink, Long orderId) {
        Order order = orderRepository.findById(orderId).get();

        Payment payment = order.getPayment();
        payment.setPaymentLink(paymentLink);
        paymentRepository.save(payment);

        return orderRepository.save(order);
    }

//    public GetOrderResponse updateOrderStatus(Long orderId, OrderStatus orderStatus) {
//        Order order = orderRepository.findById(orderId).get();
//        order.setOrderStatus(orderStatus);
//        order.setUpdatedDate(LocalDateTime.now());
//        Order orderDb = orderRepository.save(order);
//        return modelMapper.map(orderDb, GetOrderResponse.class);
//    }

    public GetOrderResponse updateOrderStatusAndEmployee(Long orderId, UpdateOrderStatusAndEmployee updateOrderStatusAndEmployee) {
        Order order = orderRepository.findById(orderId).get();

        Employee employee = employeeService.getEmployee(updateOrderStatusAndEmployee.getEmployeeId().getId());
        order.setEmployee(employee);


        order.setOrderStatus(updateOrderStatusAndEmployee.getOrderStatus());
        order.setUpdatedDate(LocalDateTime.now());
        Order orderDb = orderRepository.save(order);
        return modelMapper.map(orderDb, GetOrderResponse.class);
    }
}
