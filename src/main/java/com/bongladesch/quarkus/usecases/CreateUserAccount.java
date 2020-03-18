package com.bongladesch.quarkus.usecases;

import com.bongladesch.quarkus.usecases.interfaces.IUserRepository;
import com.bongladesch.quarkus.usecases.model.UserData;
import com.bongladesch.quarkus.usecases.validation.UserAccountValidator;
import com.bongladesch.quarkus.usecases.exception.UserAlreadyExistsException;
import com.bongladesch.quarkus.usecases.exception.UserValidationException;
import com.bongladesch.quarkus.usecases.interfaces.IIdGenerator;
import com.bongladesch.quarkus.usecases.interfaces.IIdentityProvider;
import com.bongladesch.quarkus.usecases.interfaces.ILogger;
import com.bongladesch.quarkus.usecases.interfaces.IS3Bucket;
import com.bongladesch.quarkus.core.UserAccount;

/**
 * Usecase implementation of 'CreateUser'.
 * This usecase creates a user account on registration of new users.
 * The account is created based on provided user data.
 */
public final class CreateUserAccount {

    private final ILogger logger;
	private final IUserRepository repository;
    private final IIdGenerator idGenerator;
    private final IIdentityProvider identityProvider;
    private final IS3Bucket bucket;

    /**
     * Constructor of the 'CreateUserAccount' usecase object.
     * All runtime dependencies for this usecase must be injected on creation.
     * @param repository Repository to manage the persistence of user account data
     * @param idGenerator ID generator to generate unique IDs for all users
     * @param logger Logging interface to log on different levels during the usecase process
     * @param identityProvider Interface to the identity provider to create login
     * @param bucket Interface for the creation of a S3 bucket for the users documents
     */
    public CreateUserAccount(
        final IUserRepository repository,
        final IIdGenerator idGenerator,
        final ILogger logger,
        final IIdentityProvider identityProvider,
        final IS3Bucket bucket) {
        this.logger = logger;
        this.repository = repository;
        this.idGenerator = idGenerator;
        this.identityProvider = identityProvider;
        this.bucket = bucket;
    }

    /**
     * Create a user account by given user data to be able to login.
     * This usecase includes the creation of a 'login' in the identity provider,
     * and creation of a S3 bucket for storing of documents.
     * The user account related data are stored in the repository database.
     * @param userData User data to create a new user account
     * @return The user data to create a valid account
     */
    public UserData create (final UserData userData)
    throws UserValidationException, UserAlreadyExistsException {
        // Create user account object by given parameters
        UserAccount userAccount = UserAccount.builder()
			.id(idGenerator.generateId())
            .email(userData.getEmail())
            .password(userData.getPassword())
			.firstName(userData.getFirstName())
			.lastName(userData.getLastName())
            .build();
        // Add generated id to incomming userData object for return with id
        userData.setId(userAccount.getId());
        // Validate the created user account
        UserAccountValidator.validateUserAccount(userAccount);
        if (repository.findByEmail(userAccount.getEmail()) != null) {
            logger.info("User with email-address " + userAccount.getEmail() + " already exists");
            throw new UserAlreadyExistsException(userAccount.getEmail());
        }
        logger.info("Created new valid user account with id: " + userAccount.getId());
        // Create login at identity provider
        identityProvider.createUser(userData);
        logger.debug("Added user account with id: " + userAccount.getId() + " to identity provider");
        /*
         * The following step (creation of S3 bucket) may be replaced by sending a event
         * over a message bus if the domains 'users' and 'documents' will be divided in
         * different services in order to decrease the amount of dependencies of the single services.
         */
        // Create S3 bucket for the users documents
        bucket.createBucket(userAccount.getId());
        logger.debug("Created S3 bucket for user account with id: " + userAccount.getId());
        // Persist user data to database
        repository.create(userData);
        logger.debug("Persisted user account with id: " + userAccount.getId() + " in database");
        return userData;
    }
}