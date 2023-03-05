package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.dto.order.PlaceOrderDTO;
import com.gearvmstore.GearVM.service.CustomerService;
import com.gearvmstore.GearVM.service.OrderService;
import com.gearvmstore.GearVM.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final JwtUtil jwtUtil;
    private final CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, JwtUtil jwtUtil, CustomerService customerService) {
        this.orderService = orderService;
        this.jwtUtil = jwtUtil;
        this.customerService = customerService;
    }

    @PostMapping(value = "/place-order")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO, @RequestHeader(name = "Authorization") String header) {
        
        if (header == null)
            return new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);

        String token = header.substring(7);

        if (jwtUtil.validateJwtToken(token))
            return ResponseEntity.ok().body(orderService.placeNewOrder(placeOrderDTO, token));
        return new ResponseEntity<>("Token expired", HttpStatus.UNAUTHORIZED);
    }
}
