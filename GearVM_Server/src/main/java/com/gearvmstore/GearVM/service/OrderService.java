package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.*;
import com.gearvmstore.GearVM.model.dto.order.*;
import com.gearvmstore.GearVM.model.response.GetOrderListResponse;
import com.gearvmstore.GearVM.model.response.GetOrderResponse;
import com.gearvmstore.GearVM.repository.*;
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
    private final DiscountRepository discountRepository;



    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductService productService, EmployeeService employeeService, JwtUtil jwtUtil, OrderItemRepository orderItemRepository, ModelMapper modelMapper, EntityManager em, PaymentService paymentService, PaymentRepository paymentRepository1, ShippingDetailRepository shippingDetailRepository1, ShippingDetailService shippingDetailService,
                        DiscountRepository discountRepository) {
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
        this.discountRepository = discountRepository;
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
    public GetOrderResponse placeNewOrder(PlaceOrder placeOrder, String token,String code) {
        try {
            Customer customer = customerService.getCustomer(Long.parseLong(jwtUtil.getIdFromToken(token)));
            if (customer == null)
                return null;

            Order order = new Order();

            order.setCustomer(customer);
            order.setCreatedDate(LocalDateTime.now());
            order.setUpdatedDate(LocalDateTime.now());
            order.setTotalPrice(placeOrder.getTotalPrice());
            order.setOrderStatus(OrderStatus.PAYMENT_PENDING);

            if(code != "") {
                Discount discount = discountRepository.findByCode(code);
                discount.setUsed(true);
                discountRepository.save(discount);
                order.setDiscount(discount);
            }

            Payment payment = new Payment();
            paymentRepository.save(payment);

            ShippingDetail shippingDetail = new ShippingDetail();
            shippingDetail.setPhoneNumber(customer.getPhoneNumber());
            shippingDetailRepository.save(shippingDetail);

            order.setPayment(payment);
            order.setShippingDetail(shippingDetail);

            Order savedOrder = orderRepository.save(order);

            List<OrderItemDto> orderItemDtos = placeOrder.getOrderItemDtos();
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
            System.out.println("123");
            System.out.println(getOrder(savedOrder.getId()));
            return getOrder(savedOrder.getId());
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public GetOrderResponse placeNewOrderAlt(PlaceOrderAlt placeOrderDto, String token, PaymentMethod paymentMethod,String code) {
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

            if(code != "") {
                Discount discount = discountRepository.findByCode(code);
                discount.setUsed(true);
                discountRepository.save(discount);
                order.setDiscount(discount);
            }

            Payment payment = new Payment();
            payment.setPaymentMethod(paymentMethod);
            paymentRepository.save(payment);

            ShippingDetail shippingDetail = new ShippingDetail();
            shippingDetail.setPhoneNumber(placeOrderDto.getShippingDetailDto().getPhoneNumber());
            shippingDetail.setName(placeOrderDto.getShippingDetailDto().getName());
            shippingDetail.setAddress(placeOrderDto.getShippingDetailDto().getAddress());
            shippingDetail.setEmail(placeOrderDto.getShippingDetailDto().getEmail());
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

    public List<GetOrderListResponse> getOrders() {
        List<Order> orderList = orderRepository.findAll();
        List<GetOrderListResponse> getOrderListResponseList = new ArrayList<>();

        for (Order order : orderList) {
            GetOrderListResponse orderListResponse = modelMapper.map(order, GetOrderListResponse.class);
            getOrderListResponseList.add(orderListResponse);
        }
        return getOrderListResponseList;
    }

    public List<GetOrderListResponse> getAllOnlineOrdersAndPaidDirectOrders() {
        List<Order> paidDirectOrder = orderRepository.findAllByIsDirectAndOrderStatusNotOrderByCreatedDateDesc
                (true, OrderStatus.DIRECT_PENDING);

        List<Order> onlineOrder = orderRepository.findAllByIsDirectOrderByCreatedDateDesc(false);

        paidDirectOrder.addAll(onlineOrder);

        List<GetOrderListResponse> getOrderListResponseList = new ArrayList<>();
        for (Order order : paidDirectOrder) {
            GetOrderListResponse orderListResponse = modelMapper.map(order, GetOrderListResponse.class);
            getOrderListResponseList.add(orderListResponse);
        }
        return getOrderListResponseList;
    }

    public List<GetOrderListResponse> getOrderListByCurrentCustomerToken(String token) {
        Customer customer = customerService.getCustomer(Long.parseLong(jwtUtil.getIdFromToken(token)));
        if (customer == null)
            return null;

        List<Order> orderList = orderRepository.findAllByCustomerIdAndIsDirectOrderByUpdatedDateDesc
                (customer.getId(), false);
        List<GetOrderListResponse> getOrderListResponseList = new ArrayList<>();

        for (Order order : orderList) {
            GetOrderListResponse orderListResponse = modelMapper.map(order, GetOrderListResponse.class);
            getOrderListResponseList.add(orderListResponse);
        }
        return getOrderListResponseList;
    }

    public List<GetOrderListResponse> getOrderListByOrderStatus(OrderStatus orderStatus) {
        List<Order> orderList = orderRepository.findAllByOrderStatusOrderByCreatedDateDesc(orderStatus);
        List<GetOrderListResponse> getOrderListResponseList = new ArrayList<>();

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
        if (newOrderStatus == OrderStatus.SHIPPING && !order.isDirect())
            for (OrderItem orderItem : order.getOrderItems()) {
                productService.reduceQuantity(orderItem.getProduct(), orderItem.getQuantity());
            }

        // Add quantity if order is from confirmed to something else
        if ((oldOrderStatus == OrderStatus.SHIPPING || oldOrderStatus == OrderStatus.SHIP_SUCCESS)
                && (newOrderStatus != OrderStatus.SHIPPING || newOrderStatus != OrderStatus.SHIP_SUCCESS)
                && !order.isDirect()) {
            for (OrderItem orderItem : order.getOrderItems()) {
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
                productService.reduceQuantity(orderItem.getProduct(), quantity);
                orderItemRepository.save(orderItem);

                return updateTotalPrice(order);
            }
        }

        OrderItem orderItemToAdd = new OrderItem();
        Product product = productService.getProduct(Long.parseLong(updateOrderItem.getProductId()));

        orderItemToAdd.setProduct(product);
        orderItemToAdd.setId(order.getId());
        orderItemToAdd.setQuantity(quantity);
        orderItemToAdd.setPrice(product.getPrice());
        orderItemToAdd.setOrder(order);

        order.setTotalPrice(orderItemToAdd.getPrice() * orderItemToAdd.getQuantity());

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
                productService.addQuantity(orderItem.getProduct(), quantity);

                // If quantity of order item = 0 --> remove from cart
                if (newQuantity == 0) {
                    orderItemRepository.delete(orderItem);
                } else {
                    orderItem.setQuantity(newQuantity);
                    orderItemRepository.save(orderItem);
                }
                order.setTotalPrice(order.getTotalPrice() - (orderItem.getPrice() * updateOrderItem.getAmount()));
            }
        }
        if (order.getTotalPrice() == 0) {
            orderRepository.delete(order);
            return null;
        } else return orderRepository.save(order);
    }

    public Order updateTotalPrice(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        double totalPrice = 0;

        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        }

        order.setTotalPrice(totalPrice);

        if (totalPrice == 0) {
            orderRepository.delete(order);
            return null;
        }

        return orderRepository.save(order);
    }

    public Order processDirectOrderPayment(ProcessDirectOrderPayment processDirectOrderPayment) {
        Order order = orderRepository.findById(processDirectOrderPayment.getOrderId()).get();
        Employee employee = employeeService.getEmployee(processDirectOrderPayment.getEmployeeId());

        if (processDirectOrderPayment.getShippingAddress() == null) {
            order.setOrderStatus(OrderStatus.SHIP_SUCCESS);
            order.setShippingDetail(null);
        } else {
            order.setOrderStatus(OrderStatus.PAYMENT_DONE);
            ShippingDetail shippingDetail = new ShippingDetail();
            shippingDetail.setName(processDirectOrderPayment.getShippingName());
            shippingDetail.setPhoneNumber(processDirectOrderPayment.getShippingPhone());
            shippingDetail.setAddress(processDirectOrderPayment.getShippingAddress());
            shippingDetailRepository.save(shippingDetail);
            order.setShippingDetail(shippingDetail);
        }

        Payment payment = new Payment();
        payment.setPaymentMethod(processDirectOrderPayment.getPaymentMethod());
        paymentRepository.save(payment);
        order.setPayment(payment);

        order.setEmployee(employee);
        order.setCreatedDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
