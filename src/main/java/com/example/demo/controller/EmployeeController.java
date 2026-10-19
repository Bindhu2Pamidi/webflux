package com.example.demo.controller;
import com.example.demo.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController{

    @Autowired
    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/employee")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee){
        return service.createEmployee(employee);
    }

    @GetMapping("/employee")
    public List<EmployeeEntity> getEmployee(){
        return service.getEmployee();
    }
}