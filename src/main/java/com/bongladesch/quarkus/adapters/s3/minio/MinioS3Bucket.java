package com.bongladesch.quarkus.adapters.s3.minio;

import com.bongladesch.quarkus.usecases.interfaces.IS3Bucket;

public class MinioS3Bucket implements IS3Bucket {

    public MinioS3Bucket() {
        // TODO initialize connection to minio api
    }

    public boolean createBucket(String id) {
        // TODO implement
        return true;
    }

    public boolean deleteBucket(String id) {
        // TODO implement
        return true;
    }
}