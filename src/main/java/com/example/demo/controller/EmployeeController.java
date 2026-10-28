package com.example.demo.controller;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/employee")
    public Mono<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee) {
        return service.createEmployee(employee);
    }

    @GetMapping("/employee")
    public Flux<EmployeeEntity> getEmployee() {
        return service.getEmployee();
    }
}