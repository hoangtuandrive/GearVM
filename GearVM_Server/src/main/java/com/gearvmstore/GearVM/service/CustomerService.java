package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Customer;
import com.gearvmstore.GearVM.repository.CustomerRepository;
import com.gearvmstore.GearVM.utility.HashPasswordUtil;
import com.gearvmstore.GearVM.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final HashPasswordUtil hashPasswordUtil;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, HashPasswordUtil hashPasswordUtil, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.hashPasswordUtil = hashPasswordUtil;
        this.jwtUtil = jwtUtil;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).get();
    }

    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer c = customerRepository.findById(customerId).get();
        c.setName(customerDetails.getName());
        c.setGender(customerDetails.getGender());
        c.setPhoneNumber(customerDetails.getPhoneNumber());
        c.setDateOfBirth(customerDetails.getDateOfBirth());
        c.setAddress(customerDetails.getAddress());
        c.setEmail(customerDetails.getEmail());
        return customerRepository.save(c);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer createCustomer(Customer c) {
        return customerRepository.save(c);
    }

    public boolean checkEmailExist(String email) {
        return !(customerRepository.findByEmail(email) == null);
    }

    public Customer register(Customer customer) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (customerRepository.findByEmail(customer.getEmail()) == null) {
            customer.setPassword(hashPasswordUtil.generatePasswordHash(customer.getPassword()));
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer validateLogin(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = customerRepository.findByEmail(username);

        if (customer == null) customer = customerRepository.findByPhoneNumber(username);

        if (customer == null) return null;

        if (hashPasswordUtil.validatePassword(password, customer.getPassword()))
            return customer;
        return null;
    }

    public String generateToken(String id, String email) {
        return jwtUtil.generateJwtToken(id, email);
    }
}
