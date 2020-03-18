package com.bongladesch.quarkus.core;

/**
* This class represents a user account of the PlessMe system.
* A user account owns a whole collection of documents.
* He is able to handle these documents by uploading or
* downloading after scrolling through all documents.
*/
public class UserAccount {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    
    /**
     * Default constructor to create a new user by his name.
     * The name is in this case the sure and last name as one string.
     * @param id ID of the new user account
     * @param email Email of the user account
     * @param password Password of the user account
     * @param firstName FirstName of the user account
     * @param lastName LastName of the user account
     */
    private UserAccount(final String id, final String email, final String password, final String firstName, final String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserAccountBuilder builder() {
        return new UserAccountBuilder();
    }

    /**
     * Implementation of an inner class builder pattern.
     * This pattern avoids setter methods on the UserAccount class.
     */
    public static class UserAccountBuilder {
        private String id;
        private String email;
        private String password;
		private String firstName;
        private String lastName;
        
        /**
         * Empty default constructor for simple instance creation.
         */
        UserAccountBuilder() {}

        /**
         * Set an ID to an account before creation.
         * @param id Identification
         * @return The UserAccountBuilder instance
         */
        public UserAccountBuilder id(final String id) {
            this.id = id;
            return this;
        }

        /**
         * Set an E-Mail to an account before creation.
         * @param email E-Mail address of the user
         * @return The UserAccountBuilder instance
         */
        public UserAccountBuilder email(final String email) {
            this.email = email;
            return this;
        }

        /**
         * Set the password of an user account before building.
         * @param password Password of the user
         * @return The UserAccountBuilder instance
         */
        public UserAccountBuilder password(final String password) {
            this.password = password;
            return this;
        }

        /**
         * Set the first name to an account before creation.
         * @param firstName First name of the user
         * @return The UserAccountBuilder instance
         */
        public UserAccountBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Set the last name to an account before creation.
         * @param lastName Last name of the user
         * @return The UserAccountBuilder instance
         */
        public UserAccountBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Creation of the desired user after call of setters.
         * @return The created UserAccount object
         */
        public UserAccount build() {
            return new UserAccount(this.id, this.email, this.password, this.firstName, this.lastName);
        }
    }

    /**
     * Get the system ID of a user.
     * @return The ID of the user
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the E-Mail adress of an user account.
     * @return E-Mail address of the user account
     */
    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * Get the first name of a user.
     * @return The first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Get the last name of a user.
     * @return The last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return "UserAccount: {\n" +
            "\tid='" + id + '\'' +
            ",\n\temail='" + email + '\'' +
			",\n\tlastName='" + lastName + '\'' +
			",\n\tfirstName='" + firstName + '\'' +
			"\n}";
    }
}