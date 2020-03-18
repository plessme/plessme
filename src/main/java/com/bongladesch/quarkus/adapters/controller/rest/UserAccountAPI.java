package com.bongladesch.quarkus.adapters.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bongladesch.quarkus.adapters.controller.Config;
import com.bongladesch.quarkus.usecases.CreateUserAccount;
import com.bongladesch.quarkus.usecases.exception.UserAlreadyExistsException;
import com.bongladesch.quarkus.usecases.exception.UserValidationException;
import com.bongladesch.quarkus.usecases.model.UserData;

/**
 * TODO ....
 */
@Path("/users")
public class UserAccountAPI {

    final Config config;

    public UserAccountAPI() {
        this.config = new Config();
    }

    /**
     * Rest API endpoint implementation for the createUser usecase.
     * @param email E-Mail address of the new user account
     * @param password Passwor of the new user account
     * @param firstName Firstname of the new user account
     * @param lastName Lastname of the new user account
     * @return A valid HTTP reponse
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(
        @QueryParam("email") String email,
        @QueryParam("password") String password,
        @QueryParam("firstname") String firstName,
        @QueryParam("lastname") String lastName) {
        // Inject dependencies to the usecase on creation
        CreateUserAccount createUserAccount = new CreateUserAccount(
            config.getUserRepository(),
            config.getIdGenerator(),
            config.getLogger(),
            config.getIdentityProvider(),
            config.getS3Bucket()
        );
        // Transform query parameters to user data object
        UserData userData = new UserData(email, password, firstName, lastName);
        try {
            createUserAccount.create(userData);
        } catch(Exception e) {
            if(e instanceof UserAlreadyExistsException) {
                return Response.status(403).build();
            } else if (e instanceof UserValidationException) {
                return Response.status(400).build();
            }
        }
        return Response.ok(userData).build();
    }
}