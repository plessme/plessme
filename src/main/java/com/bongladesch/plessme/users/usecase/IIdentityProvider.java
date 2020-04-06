package com.bongladesch.plessme.users.usecase;

import com.bongladesch.plessme.users.entity.User;

/**
 * IIdentityProvider interface provides the methods to the
 * identity providers API calls.
 */
public interface IIdentityProvider {

    /**
     * Create an enabled user with the necessary role to access plessme as "user".
     * The users ID and creation timestamp of the user is created by identity provider.
     * @param user input user data
     * @return a new user object with input user data and "id" and
     * "created" timestamp form identity provider
     */
    User createUser(User user);
}