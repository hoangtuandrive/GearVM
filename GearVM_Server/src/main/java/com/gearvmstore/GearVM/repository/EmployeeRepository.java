package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);

    List<Employee> findDistinctByIdEqualsOrNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCaseOrEmailContainingIgnoreCaseOrderByIdAsc(Long id, String name, String phoneNumber, String email);
}
