package com.bongladesch.quarkus.usecases.interfaces;

public interface IS3Bucket {
    boolean createBucket(String id);
    boolean deleteBucket(String id);
}