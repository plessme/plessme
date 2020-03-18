package com.bongladesch.quarkus.usecases.interfaces;

import com.bongladesch.quarkus.usecases.model.UserData;

public interface IIdentityProvider {
    void createUser(UserData userAccount);
}