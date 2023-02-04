package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee e) {
        return employeeService.createEmployee(e);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> readEmployees() {
        return employeeService.getEmployees();
    }

    @RequestMapping(value = "{employeeId}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable(value = "employeeId") Long id) {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "{employeeId}", method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable(value = "employeeId") Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @RequestMapping(value = "{employeeId}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable(value = "employeeId") Long id) {
        employeeService.deleteEmployee(id);
    }
}
