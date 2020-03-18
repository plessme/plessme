package com.bongladesch.quarkus.adapters.database.inmemory;

import java.util.ArrayList;
import java.util.List;

import com.bongladesch.quarkus.usecases.interfaces.IUserRepository;
import com.bongladesch.quarkus.usecases.model.UserData;

public class InMemoryUserRepository implements IUserRepository {

    private static List<UserData> userRepository = new ArrayList<UserData>();

    public void create(UserData userData) {
        userRepository.add(userData);
    }

    public UserData findByEmail(String email) {
        UserData userData;
        for(int i = 0; i < userRepository.size(); i++) {
            userData = userRepository.get(i);
            if(userData.getEmail().equals(email)) return userData;
        }
        return null;
    }
}