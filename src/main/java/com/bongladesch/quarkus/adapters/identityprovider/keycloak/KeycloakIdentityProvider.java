package com.bongladesch.quarkus.adapters.identityprovider.keycloak;

import javax.inject.Inject;

import com.bongladesch.quarkus.usecases.interfaces.IIdentityProvider;
import com.bongladesch.quarkus.usecases.model.UserData;

import org.eclipse.microprofile.rest.client.inject.RestClient;

public class KeycloakIdentityProvider implements IIdentityProvider {

    @Inject
    @RestClient
    KeycloakRestClient keycloakRestClient;

    public void createUser(UserData userData) {
        // TODO call createLogin function if ready
        //keycloakRestClient.createLogin(userData);
    }
}