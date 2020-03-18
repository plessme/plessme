package com.bongladesch.quarkus.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bongladesch.quarkus.adapters.logger.JBossLogger;
import com.bongladesch.quarkus.adapters.s3.minio.MockS3Bucket;
import com.bongladesch.quarkus.adapters.database.MockUserRepository;
import com.bongladesch.quarkus.adapters.identityProvider.keycloak.MockIdentityProvider;
import com.bongladesch.quarkus.adapters.uuid.MockUuidGenerator;
import com.bongladesch.quarkus.usecases.exception.UserAlreadyExistsException;
import com.bongladesch.quarkus.usecases.exception.UserValidationException;
import com.bongladesch.quarkus.usecases.interfaces.ILogger;
import com.bongladesch.quarkus.usecases.model.UserData;

import org.junit.jupiter.api.Test;

public class TestCreateUserAccount {

    ILogger logger;
    MockUserRepository mockUserRepository;
    MockUuidGenerator uuidGenerator;
    MockIdentityProvider identityProvider;
    MockS3Bucket s3Bucket;

    public TestCreateUserAccount() {
        logger = new JBossLogger();
        mockUserRepository = new MockUserRepository(logger);
        uuidGenerator = new MockUuidGenerator();
        identityProvider = new MockIdentityProvider();
        s3Bucket = new MockS3Bucket();
    }

    @Test
    public void testCreateUser() {
        // Create user account
        CreateUserAccount createUserAccount = new CreateUserAccount(mockUserRepository, uuidGenerator, logger, identityProvider, s3Bucket);
        UserData userData = createUserAccount.create(new UserData("me@test.com", "password", "my", "name"));

        // Assert statements
        assertEquals(userData.getId(), "UUID");
        assertEquals(userData.getEmail(), "me@test.com");
        assertEquals(userData.getPassword(), "password");
        assertEquals(userData.getFirstName(), "my");
        assertEquals(userData.getLastName(), "name");
    }

    @Test
    public void testUserAlreadyExists() {
        // Configure mock to simulate a already existing user account
        mockUserRepository.alreadyExists();

        // Create user account
        CreateUserAccount createUserAccount = new CreateUserAccount(mockUserRepository, uuidGenerator, logger, identityProvider, s3Bucket);
        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
            createUserAccount.create(new UserData("me@test.com", "password", "my", "name"));
        });

        String expectedMessage = "me@test.com";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInvalidUserNoPassword() {
        // Create user account
        CreateUserAccount createUserAccount = new CreateUserAccount(mockUserRepository, uuidGenerator, logger, identityProvider, s3Bucket);
        Exception exception = assertThrows(UserValidationException.class, () -> {
            createUserAccount.create(new UserData("me@test.com", null, "my", "name"));
        });

        String expectedMessage = "Provided password is null or emtpy.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInvalidUserEmptyEmail() {
        // Create user account
        CreateUserAccount createUserAccount = new CreateUserAccount(mockUserRepository, uuidGenerator, logger, identityProvider, s3Bucket);
        Exception exception = assertThrows(UserValidationException.class, () -> {
            createUserAccount.create(new UserData("", "password", "my", "name"));
        });

        String expectedMessage = "Provided e-mail address is null or emtpy.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}