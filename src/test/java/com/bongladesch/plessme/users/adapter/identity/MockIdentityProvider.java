package com.bongladesch.plessme.users.adapter.identity;

import javax.enterprise.context.ApplicationScoped;

import com.bongladesch.plessme.users.adapter.keycloak.KeycloakIdentityProvider;
import com.bongladesch.plessme.users.entity.User;
import com.bongladesch.plessme.users.entity.User.UserBuilder;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped 
public class MockIdentityProvider extends KeycloakIdentityProvider {

    @Override
    public User createUser(User user) {
        UserBuilder builder = new UserBuilder();
        builder.email(user.getEmail()).
                password(user.getPassword()).
                firstName(user.getFirstName()).
                lastName(user.getLastName());
        builder.id("UUID").created(123L);
        return builder.build();
    }
}