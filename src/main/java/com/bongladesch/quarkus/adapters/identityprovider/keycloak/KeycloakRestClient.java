package com.bongladesch.quarkus.adapters.identityprovider.keycloak;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bongladesch.quarkus.usecases.model.UserData;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v2")
@RegisterRestClient(configKey="keycloak-api")
public interface KeycloakRestClient {

    // TODO finalize here
    @POST
    @Path("/name/{name}")
    @Produces("application/json")
    @Consumes("application/json")
    void createLogin(@PathParam UserData userData);
}