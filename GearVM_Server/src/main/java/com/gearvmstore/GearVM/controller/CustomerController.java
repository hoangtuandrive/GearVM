package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Customer;
import com.gearvmstore.GearVM.model.dto.user.LoginDTO;
import com.gearvmstore.GearVM.model.dto.user.RegisterDTO;
import com.gearvmstore.GearVM.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Customer register(@RequestBody RegisterDTO registerDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = modelMapper.map(registerDTO, Customer.class);
        return customerService.register(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = customerService.validateLogin(loginDTO.getEmail(), loginDTO.getPassword());
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
}