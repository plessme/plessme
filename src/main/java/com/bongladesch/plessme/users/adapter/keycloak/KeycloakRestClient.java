package com.bongladesch.plessme.users.adapter.keycloak;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * KeycloakRestClient implements REST API
 * requests for user management in Keycloak.
 */
@Path("/auth")
@RegisterRestClient(configKey="keycloak-api")
public interface KeycloakRestClient {

    /**
     * Request oidc access token for the realm
     * administrator of Keycloak realm.
     * @param realm Realm name of Keycloak realm
     * @param username User name of realm administrator
     * @param password Password of realm administrator
     * @param grantType Set grant_type to 'password'
     * @param clientId Client id of Keycloak realm
     * @return A keycloak token response containing the access token.
     */
    @POST
    @Path("/realms/{realm}/protocol/openid-connect/token")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    KeycloakTokenResponse getToken(
        @PathParam("realm") String realm,
        @FormParam("username") String username,
        @FormParam("password") String password,
        @FormParam("grant_type") String grantType,
        @FormParam("client_id") String clientId);
    
    /**
     * Create a Keycloak user from Keycloak user JSON API object.
     * @param realm Realm name of Keycloak realm
     * @param auth Bearer token for OIDC authorization
     * @param userRepresentation Keycloak user JSON API object
     * @return HTTP response
     */
    @POST
    @Path("admin/realms/{realm}/users")
    @Consumes("application/json")
    @Produces("application/json")
    Response createUser(
        @PathParam("realm") String realm,
        @HeaderParam("Authorization") String auth,
        KeycloakUserRepresentation userRepresentation);
    
    /**
     * Get a created Keycloak user by email address.
     * @param realm Realm name of Keycloak realm
     * @param auth Bearer token for OIDC authorization
     * @param email Email address of the Keycloak user
     * @return A list of Keycloak user JSON API objects with matching email (1).
     */
    @GET
    @Path("admin/realms/{realm}/users")
    @Consumes("application/json")
    @Produces("application/json")
    KeycloakUserRepresentation[] getUserByEmail(
        @PathParam("realm") String realm,
        @HeaderParam("Authorization") String auth,
        @QueryParam("email") String email);
}