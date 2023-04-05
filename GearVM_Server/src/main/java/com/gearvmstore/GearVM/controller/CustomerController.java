package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Customer;
import com.gearvmstore.GearVM.model.dto.user.LoginDTO;
import com.gearvmstore.GearVM.model.dto.user.RegisterDTO;
import com.gearvmstore.GearVM.service.CustomerService;
import com.gearvmstore.GearVM.utility.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService, JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = modelMapper.map(registerDTO, Customer.class);

        Customer newAccount = customerService.register(customer);
        if (newAccount == null) {
            return ResponseEntity.badRequest().body("Account existed");
        }

        return ResponseEntity.ok().body(newAccount);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = customerService.validateLogin(loginDTO.getUsername(), loginDTO.getPassword());

        if (customer == null) {
            return ResponseEntity.badRequest().body("Login failed");
        }

        return ResponseEntity.ok().body(customerService.generateToken(customer.getId().toString(), customer.getEmail()));
    }

    @GetMapping(value = "/email-exist/{email}")
    public ResponseEntity<String> checkEmailExist(@PathVariable(value = "email") String email) {
        if (customerService.checkEmailExist(email)) return ResponseEntity.ok().body("true");
        else return ResponseEntity.ok().body("false");
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer c) {
        return new ResponseEntity<Customer>(customerService.createCustomer(c), HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "customerId") Long id, @RequestBody Customer customerDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerDetails));
    }

    @GetMapping(value = "/current-user")
    public ResponseEntity<?> checkCurrentUser(@RequestHeader(name = "Authorization") String header) {
        if (header == null)
            return new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);

        String token = header.substring(7);

        if (jwtUtil.validateJwtToken(token))
            return ResponseEntity.ok().body(customerService.getCustomer(Long.parseLong(jwtUtil.getIdFromToken(token))));
        return new ResponseEntity<String>("Token expired", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/phonenumber/{phoneNumber}")
    public ResponseEntity<?> getCustomerByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        if (customer == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping(value = "/get-all-phoneNumber")
    public ResponseEntity<?> getAllPhoneNumber() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllByPhoneNumber());
    }
}