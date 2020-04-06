package com.bongladesch.plessme.common.adapter.uuid;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.bongladesch.plessme.common.usecase.IIdGenerator;

/**
 * UuidGenerator is a concret implementation of the
 * IIdGenerator interface which is used by the "usecase"
 * layer of all PlessMe components.
 */
@ApplicationScoped
public class UuidGenerator implements IIdGenerator {

    /**
     * Generate a unique ID and return as string
     * @return generated ID
     */
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}