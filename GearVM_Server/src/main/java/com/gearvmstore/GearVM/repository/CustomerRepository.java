package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    Customer findByPhoneNumber(String phoneNumber);

    Customer findByPhoneNumberStartingWith(String phoneNumber);

    @Query("SELECT DISTINCT c.phoneNumber FROM Customer c")
    List<String> findAllPhoneNumber();


    public Customer findByResetPasswordToken(String token);
}
