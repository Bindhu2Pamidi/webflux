package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

@Component
public class DynamoDbTableInitializer implements ApplicationRunner {

    private final DynamoDbAsyncClient client;
    private final String tableName;

    public DynamoDbTableInitializer(DynamoDbAsyncClient client, @Value("${aws.dynamodb.table}") String tableName) {
        this.client = client;
        this.tableName = tableName;
    }

    @Override
    public void run(ApplicationArguments args) {
        client.createTable(r -> r
                        .tableName(tableName)
                        .attributeDefinitions(a -> a.attributeName("id").attributeType("N"))
                        .keySchema(k -> k.attributeName("id").keyType("HASH"))
                        .billingMode("PAY_PER_REQUEST"))
                .exceptionally(e -> null)
                .join();
    }
}