package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.repository.EmployeeRepository;
import com.gearvmstore.GearVM.utility.HashPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (!checkEmailExist(e.getEmail())) {
            e.setPassword(hashPasswordUtil.generatePasswordHash("abc12345"));
            return employeeRepository.save(e);
        }
        return null;
    }

    public boolean checkEmailExist(String email) {
        return !(employeeRepository.findByEmail(email) == null);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByFilter(String id, String name, String phoneNumber, String email) {
        try {
            return employeeRepository.findDistinctByIdEqualsOrNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCaseOrEmailContainingIgnoreCaseOrderByIdAsc(Long.parseLong(id), name, phoneNumber, email);
        } catch (NumberFormatException e) {
            return employeeRepository.findDistinctByIdEqualsOrNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCaseOrEmailContainingIgnoreCaseOrderByIdAsc(null, name, phoneNumber, email);
        }
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
        e.setRole(employeeDetails.getRole());
        return employeeRepository.save(e);
    }

    public Employee updateWorkStatus(Long employeeId, String status) {
        Employee e = employeeRepository.findById(employeeId).get();
        e.setWorkStatus(status.equalsIgnoreCase("true"));
        return employeeRepository.save(e);
    }

    public Employee validateLogin(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Employee employee = employeeRepository.findByEmail(username);

        if (employee == null) return null;

        if (hashPasswordUtil.validatePassword(password, employee.getPassword()))
            return employee;
        return null;
    }
}
