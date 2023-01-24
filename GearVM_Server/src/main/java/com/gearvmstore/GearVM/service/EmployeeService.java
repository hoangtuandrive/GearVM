package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee e) {
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
        return e;
    }
}
