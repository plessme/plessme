package com.bongladesch.plessme.common.usecase;

/**
 * IIdGenerator defines an interface to
 * generate unique IDs by any id generator.
 */
public interface IIdGenerator {

    /**
     * Generate a unique ID and return as string
     * @return generated ID
     */
    String generateId();
}