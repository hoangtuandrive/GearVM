package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Order;
import com.gearvmstore.GearVM.model.OrderStatus;
import com.gearvmstore.GearVM.model.PaymentMethod;
import com.gearvmstore.GearVM.model.dto.order.*;
import com.gearvmstore.GearVM.service.DiscountService;
import com.gearvmstore.GearVM.service.OrderService;
import com.gearvmstore.GearVM.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private  final DiscountService discountService;
    private final JwtUtil jwtUtil;

    @Autowired
    public OrderController(OrderService orderService, DiscountService discountService, JwtUtil jwtUtil) {
        this.orderService = orderService;
        this.discountService = discountService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("online-and-paidDirect")
    public ResponseEntity<?> getAllOnlineOrdersAndPaidDirectOrders() {
        return new ResponseEntity<>(orderService.getAllOnlineOrdersAndPaidDirectOrders(), HttpStatus.OK);
    }

    @GetMapping("current-customer")
    public ResponseEntity<?> getOrderListByCurrentCustomerToken(@RequestHeader(name = "Authorization") String header) {
        if (header == null)
            return new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);

        String token = header.substring(7);

        if (jwtUtil.validateJwtToken(token))
            return new ResponseEntity<>(orderService.getOrderListByCurrentCustomerToken(token), HttpStatus.OK);
        return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/direct-pending")
    public ResponseEntity<?> getDirectPendingOrderList() {
        return new ResponseEntity<>(orderService.getOrderListByOrderStatus(OrderStatus.DIRECT_PENDING), HttpStatus.OK);
    }

    @GetMapping(value = "ship-success")
    public ResponseEntity<?> getDeliveredOrderList() {
        return new ResponseEntity<>(orderService.getOrderListByOrderStatus(OrderStatus.SHIP_SUCCESS), HttpStatus.OK);
    }

    @GetMapping(value = "/get-direct-pending")
    public ResponseEntity<?> getDirectPendingOrder(@RequestParam("customerName") String customerName,
                                                   @RequestParam("customerPhone") String customerPhone) {
        return new ResponseEntity<>(orderService.getDirectPendingOrder(customerName, customerPhone), HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<?> findOrder(@PathVariable(value = "orderId") Long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }


    @PostMapping(value = "/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrder placeOrder, @RequestHeader(name = "Authorization") String header) {
        if (header == null)
            return new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);

        String token = header.substring(7);

        if (jwtUtil.validateJwtToken(token))

            return ResponseEntity.ok().body(orderService.placeNewOrder(placeOrder, token,placeOrder.getCode()));
        return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/place-order-alt/{paymentMethod}")
    public ResponseEntity<?> placeOrderAlt(@RequestBody PlaceOrderAlt placeOrderAlt,
                                           @RequestHeader(name = "Authorization") String header,
                                           @PathVariable(value = "paymentMethod") PaymentMethod paymentMethod) {

        if (header == null)
            return new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);

        String token = header.substring(7);

        if (jwtUtil.validateJwtToken(token))
            return ResponseEntity.ok().body(orderService.placeNewOrderAlt(placeOrderAlt, token, paymentMethod,placeOrderAlt.getCode()));
        return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping(value = "/update-orderStatus/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable(value = "orderId") Long id,
                                               @RequestBody UpdateOrderStatusAndEmployee updateOrderStatusAndEmployee) throws MessagingException, UnsupportedEncodingException {
                discountService.SendDiscount(id,updateOrderStatusAndEmployee);
                if(discountService.GetIdDiscount(id) != null){
                    discountService.UpdateisUsedDiscount(id,updateOrderStatusAndEmployee);
                }
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderStatusAndEmployee(id, updateOrderStatusAndEmployee));
    }

    @PatchMapping(value = "/update-orderStatus-employee/{orderId}")
    public ResponseEntity<?> updateOrderStatusAndEmployee(@PathVariable(value = "orderId") Long id,
                                                          @RequestBody UpdateOrderStatusAndEmployee updateOrderStatusAndEmployee) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderStatusAndEmployee(id, updateOrderStatusAndEmployee));
    }

    @PostMapping(value = "/create-directOrder/{customerId}")
    public ResponseEntity<?> createDirectOrder(@PathVariable(value = "customerId") Long customerId) {
        Order order = orderService.createDirectOrder(customerId);
        if (order == null)
            return ResponseEntity.status(HttpStatus.FOUND).body("");
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PatchMapping(value = "/update-add-orderItem")
    public ResponseEntity<?> updateAddOrderItem(@RequestBody UpdateOrderItem updateOrderItem) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateAddOrderItem(updateOrderItem));
    }

    @PatchMapping(value = "/update-reduce-orderItem")
    public ResponseEntity<?> updateReduceOrderItem(@RequestBody UpdateOrderItem updateOrderItem) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateReduceOrderItem(updateOrderItem));
    }

    @PatchMapping(value = "/process-directOrder-payment")
    public ResponseEntity<?> processDirectOrderPayment(@RequestBody ProcessDirectOrderPayment processDirectOrderPayment) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.processDirectOrderPayment(processDirectOrderPayment));
    }
}
