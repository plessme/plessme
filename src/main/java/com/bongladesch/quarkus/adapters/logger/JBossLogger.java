package com.bongladesch.quarkus.adapters.logger;

import com.bongladesch.quarkus.usecases.interfaces.ILogger;

import org.jboss.logging.Logger;

public class JBossLogger implements ILogger {

    private static final Logger LOGGER = Logger.getLogger(JBossLogger.class);

    public void debug(String message) {
        LOGGER.debug(message);
    }

    public void info(String message) {
        LOGGER.info(message);
    }

    public void error(String message, Exception exception) {
        LOGGER.error(message, exception);
    }
}