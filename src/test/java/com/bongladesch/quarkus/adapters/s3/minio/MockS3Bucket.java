package com.bongladesch.quarkus.adapters.s3.minio;

public class MockS3Bucket extends MinioS3Bucket {
    @Override
    public boolean createBucket(String id) {
        return true;
    }

    @Override
    public boolean deleteBucket(String id) {
        return true;
    }
}