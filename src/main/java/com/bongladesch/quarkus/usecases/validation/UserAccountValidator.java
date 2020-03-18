package com.bongladesch.quarkus.usecases.validation;

import com.bongladesch.quarkus.core.UserAccount;
import com.bongladesch.quarkus.usecases.exception.UserValidationException;

/**
 * UserAccountValidator validates a user account regarding all mandatory fields.
 */
public class UserAccountValidator {
    
    /**
     * Validates if the user accounts e-mail address and password are not null or emtpy.
     * @param userAccount UserAccount the needs to be validated.
     */
    public static void validateUserAccount(UserAccount userAccount) throws UserValidationException {
        // Check if email is null or emtpy string
        if(userAccount.getEmail() == null || userAccount.getEmail().length() == 0) {
            throw new UserValidationException("Provided e-mail address is null or emtpy.");
        }
        // Check if password is null or emtpy string
        if(userAccount.getPassword() == null || userAccount.getPassword().length() == 0) {
            throw new UserValidationException("Provided password is null or emtpy.");
        }
    }
}