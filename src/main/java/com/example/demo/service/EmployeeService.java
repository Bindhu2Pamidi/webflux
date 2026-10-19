package com.example.demo.service;

import com.example.demo.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Service
public class EmployeeService {

    private final DynamoDbTable<EmployeeEntity> employeeTable;

    public EmployeeService(
            DynamoDbEnhancedClient enhancedClient,
            @Value("${aws.dynamodb.table}") String tableName) {
        this.employeeTable =
                enhancedClient.table(tableName, TableSchema.fromBean(EmployeeEntity.class));
    }

    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        employeeTable.putItem(employee);
        return employee;
    }

    public List<EmployeeEntity> getEmployee() {
        return employeeTable.scan()
                .items()
                .stream()
                .toList();
    }
}