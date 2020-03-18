package com.bongladesch.quarkus.adapters.uuid;

import java.util.UUID;

import com.bongladesch.quarkus.usecases.interfaces.IIdGenerator;

public class UuidGenerator implements IIdGenerator {

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}