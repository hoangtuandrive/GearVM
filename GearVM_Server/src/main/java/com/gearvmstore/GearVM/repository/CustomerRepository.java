package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //    @Query("SELECT count(c)>0 from Customer c where customer.email = ?1")
    boolean existsByEmail(String email);

    Customer findByEmail(String email);
}
