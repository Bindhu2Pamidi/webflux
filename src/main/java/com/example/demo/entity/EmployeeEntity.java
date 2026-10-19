package com.example.demo.entity;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
public class EmployeeEntity {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;

    @DynamoDbPartitionKey
    public int getId() {
        return id;
    }
}
