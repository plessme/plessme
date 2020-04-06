package com.bongladesch.plessme.users.adapter.keycloak;

import java.util.LinkedList;
import java.util.List;

import com.bongladesch.plessme.users.entity.User;

/**
 * KeycloakUserRepresentation represents a Keycloak API user
 * object for creation and request of users with credentials,
 * groups and roles.
 */
public class KeycloakUserRepresentation {
    public String id;
    public Long createdTimestamp;
    public String username;
    public Boolean enabled;
    public Boolean emailVerified;
    public String firstName;
    public String lastName;
    public String email;
    public List<KeycloakCredentialRepresentation> credentials;
    public List<String> groups;

    /**
     * Default constructor for JSON deserialization.
     */
    public KeycloakUserRepresentation() {}

    /**
     * Constructor for creation of KeycloakUserRespository from user data.
     * @param user user object from application logic
     */
    public KeycloakUserRepresentation(User user) {
        this.username = user.getEmail();
        this.enabled = true;
        this.emailVerified = false;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.credentials = new LinkedList<KeycloakCredentialRepresentation>();
        credentials.add(new KeycloakCredentialRepresentation(user.getPassword()));
        this.groups = new LinkedList<String>();
        groups.add("users");
    }
}