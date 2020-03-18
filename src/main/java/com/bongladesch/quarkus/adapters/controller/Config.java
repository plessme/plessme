package com.bongladesch.quarkus.adapters.controller;

import com.bongladesch.quarkus.adapters.database.inmemory.InMemoryUserRepository;
import com.bongladesch.quarkus.adapters.identityprovider.keycloak.KeycloakIdentityProvider;
import com.bongladesch.quarkus.adapters.logger.JBossLogger;
import com.bongladesch.quarkus.adapters.s3.minio.MinioS3Bucket;
import com.bongladesch.quarkus.adapters.uuid.UuidGenerator;
import com.bongladesch.quarkus.usecases.interfaces.IIdGenerator;
import com.bongladesch.quarkus.usecases.interfaces.IIdentityProvider;
import com.bongladesch.quarkus.usecases.interfaces.ILogger;
import com.bongladesch.quarkus.usecases.interfaces.IS3Bucket;
import com.bongladesch.quarkus.usecases.interfaces.IUserRepository;

public class Config {
    
    private final IIdGenerator idGenerator;
    private final ILogger logger;
    private final IUserRepository userRepository;
    private final IIdentityProvider identityProvider;
    private final IS3Bucket bucket;

    public Config() {
        idGenerator = new UuidGenerator();
        logger = new JBossLogger();
        userRepository = new InMemoryUserRepository();
        identityProvider = new KeycloakIdentityProvider();
        bucket = new MinioS3Bucket();
    }

    public IIdGenerator getIdGenerator() {
        return this.idGenerator;
    }

    public ILogger getLogger() {
        return this.logger;
    }

    public IUserRepository getUserRepository() {
        return this.userRepository;
    }

    public IIdentityProvider getIdentityProvider() {
        return this.identityProvider;
    }

    public IS3Bucket getS3Bucket() {
        return this.bucket;
    }
}