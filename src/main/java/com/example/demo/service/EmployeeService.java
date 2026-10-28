package com.example.demo.service;

import com.example.demo.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class EmployeeService {

    private final DynamoDbAsyncTable<EmployeeEntity> employeeTable;

    public EmployeeService(DynamoDbEnhancedAsyncClient enhancedClient, @Value("${aws.dynamodb.table}") String tableName) {
        this.employeeTable = enhancedClient.table(tableName, TableSchema.fromBean(EmployeeEntity.class));
    }

    public Mono<EmployeeEntity> createEmployee(EmployeeEntity employee) {
        return Mono.fromFuture(() -> employeeTable.putItem(employee))
                .thenReturn(employee);
    }

    public Flux<EmployeeEntity> getEmployee() {
        return Flux.defer(() ->
                Flux.from(employeeTable.scan().items())
        );
    }
}