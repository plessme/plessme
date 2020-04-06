package com.bongladesch.plessme.users.usecase;

import com.bongladesch.plessme.common.usecase.ILogger;
import com.bongladesch.plessme.users.entity.User;

/**
 * Usecase implementation of 'CreateUser'.
 * This usecase creates a user account on registration of new users.
 * The account is created based on provided user data.
 */
public final class UCreateUser {

    private final ILogger logger;
	private final IUserRepository repository;
    private final IIdentityProvider identityProvider;
    private final IMessageSender messageSender;

    /**
     * Constructor of the 'CreateUserAccount' usecase object.
     * All runtime dependencies for this usecase must be injected on creation.
     * @param repository Repository interface to manage the persistence of user account data
     * @param logger Logging interface to log on different levels during the usecase process
     * @param identityProvider Interface to the identity provider to create login
     * @param messageSender Interface to send messages to other components (async)
     */
    public UCreateUser(
        final IUserRepository repository,
        final ILogger logger,
        final IIdentityProvider identityProvider,
        final IMessageSender messageSender) {
        this.logger = logger;
        this.repository = repository;
        this.identityProvider = identityProvider;
        this.messageSender = messageSender;
    }

    /**
     * Create a user account by given user data to be able to login.
     * This usecase includes the creation of a 'login' in the identity provider,
     * and creation of a S3 bucket for storing of documents.
     * The user account related data are stored in the repository database.
     * @param userInput User data to create a new user account
     * @return The user data to create a valid account
     */
    public User create(final User userInput)
    throws UserValidationException, UserAlreadyExistsException {
        // Validate user input
        UserValidator.validateUser(userInput);
        if (repository.findByEmail(userInput.getEmail()) != null) {
            logger.info("User with email-address " + userInput.getEmail() + " already exists");
            throw new UserAlreadyExistsException(userInput.getEmail());
        }
        // Create login at identity provider
        User user = identityProvider.createUser(userInput);
        logger.debug("User with email " + user.getEmail() + " added to identity provider with id: " + user.getId());
        /// Send a "user_created" event with ID as payload
        messageSender.userCreated(user.getId());
        // Persist user data to database
        repository.create(user);
        logger.info("Created new valid user account with id: " + user.getId() + "in user repository");
        return user;
    }
}