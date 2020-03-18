package com.bongladesch.quarkus.usecases.interfaces;

public interface ILogger {
    void debug(String message);
    void info(String message);
    void error(String message, Exception exception);
}