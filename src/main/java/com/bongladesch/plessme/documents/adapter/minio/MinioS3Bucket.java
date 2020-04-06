package com.bongladesch.plessme.documents.adapter.minio;

import javax.enterprise.context.ApplicationScoped;

import com.bongladesch.plessme.documents.usecase.IS3Bucket;

@ApplicationScoped
public class MinioS3Bucket implements IS3Bucket {

    public boolean createBucket(String id) {
        return true;
    }

    public boolean deleteBucket(String id) {
        return true;
    }
}