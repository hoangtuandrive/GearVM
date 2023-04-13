package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.model.dto.user.LoginDto;
import com.gearvmstore.GearVM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee e) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Employee em = employeeService.createEmployee(e);
        if (em == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email exist");
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> readEmployees() {
        return employeeService.getEmployees();
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable(value = "employeeId") Long id) {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable(value = "employeeId") Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable(value = "employeeId") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PatchMapping(value = "/work-status/{employeeId}")
    public Employee updateWorkStatus(@PathVariable(value = "employeeId") Long id, @RequestBody String status) {
        return employeeService.updateWorkStatus(id, status);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Employee employee = employeeService.validateLogin(loginDTO.getUsername(), loginDTO.getPassword());

        if (employee == null) {
            return ResponseEntity.badRequest().body("Login failed");
        }

        return ResponseEntity.ok().body(employee);
    }
}
