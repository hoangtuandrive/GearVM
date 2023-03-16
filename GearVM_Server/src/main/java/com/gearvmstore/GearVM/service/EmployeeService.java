package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.repository.EmployeeRepository;
import com.gearvmstore.GearVM.utility.HashPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final HashPasswordUtil hashPasswordUtil;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, HashPasswordUtil hashPasswordUtil) {
        this.employeeRepository = employeeRepository;
        this.hashPasswordUtil = hashPasswordUtil;
    }

    public Employee createEmployee(Employee e) throws NoSuchAlgorithmException, InvalidKeySpecException {
        e.setPassword(hashPasswordUtil.generatePasswordHash("12345678a"));
        return employeeRepository.save(e);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Employee updateEmployee(Long employeeId, Employee employeeDetails) {
        Employee e = employeeRepository.findById(employeeId).get();
        e.setName(employeeDetails.getName());
        e.setGender(employeeDetails.getGender());
        e.setDateOfBirth(employeeDetails.getDateOfBirth());
        e.setAddress(employeeDetails.getAddress());
        e.setNationalId(employeeDetails.getNationalId());
        e.setPhoneNumber(employeeDetails.getPhoneNumber());
        e.setSalary(employeeDetails.getSalary());
        e.setWorkStatus(employeeDetails.isWorkStatus());
        e.setRole(employeeDetails.getRole());
        return employeeRepository.save(e);
    }

    public Employee updateWorkStatus(Long employeeId, String status) {
        Employee e = employeeRepository.findById(employeeId).get();
        e.setWorkStatus(status.equalsIgnoreCase("true"));
        return employeeRepository.save(e);
    }
}
