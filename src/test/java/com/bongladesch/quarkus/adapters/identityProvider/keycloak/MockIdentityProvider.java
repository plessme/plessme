package com.bongladesch.quarkus.adapters.identityProvider.keycloak;

import javax.enterprise.context.ApplicationScoped;

import com.bongladesch.quarkus.adapters.identityprovider.keycloak.KeycloakIdentityProvider;
import com.bongladesch.quarkus.usecases.model.UserData;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped 
public class MockIdentityProvider extends KeycloakIdentityProvider {

    @Override
    public void createUser(UserData userAccount) {}
}