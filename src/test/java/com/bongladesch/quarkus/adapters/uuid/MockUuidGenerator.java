package com.bongladesch.quarkus.adapters.uuid;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped 
public class MockUuidGenerator extends UuidGenerator {
    
    @Override
    public String generateId() {
        return "UUID";
    }
}