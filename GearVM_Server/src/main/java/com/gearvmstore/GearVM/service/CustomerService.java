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

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public boolean checkEmailExist(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer register(Customer customer) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (!customerRepository.existsByEmail(customer.getEmail())) {
            customer.setPassword(hashPasswordUtil.generatePasswordHash(customer.getPassword()));
            return customerRepository.save(customer);
        }
        return null;
    }

    public boolean validateLogin(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = customerRepository.findByEmail(email);
        System.out.println(customer.getPassword());
        if (customer.getEmail() == null) return false;
        return hashPasswordUtil.generatePasswordHash(password).equals(customer.getPassword());
    }

    public String generateToken(String email) {
        return jwtUtil.generateJwtToken(email);
    }
}
