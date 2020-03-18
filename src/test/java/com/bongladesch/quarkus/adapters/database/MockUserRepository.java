package com.bongladesch.quarkus.adapters.database;

import javax.enterprise.context.ApplicationScoped;

import com.bongladesch.quarkus.adapters.database.inmemory.InMemoryUserRepository;
import com.bongladesch.quarkus.usecases.interfaces.ILogger;
import com.bongladesch.quarkus.usecases.model.UserData;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped 
public class MockUserRepository extends InMemoryUserRepository {

    private boolean alreadyExists;

    public MockUserRepository(ILogger logger) {
        alreadyExists = false;
    }

    public void alreadyExists() {
        this.alreadyExists = true;
    }

    public void notExists() {
        this.alreadyExists = false;
    }

    @Override
    public void create(UserData userData) {}

    @Override
    public UserData findByEmail(String email) {
        if(alreadyExists) {
            return new UserData("me@test.com", "password", "my", "name");
        } else {
            return null;
        }
    }
}