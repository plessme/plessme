package com.bongladesch.quarkus.usecases.interfaces;

import com.bongladesch.quarkus.usecases.model.UserData;

public interface IUserRepository {
    void create(UserData userData);
    UserData findByEmail(String email);
}