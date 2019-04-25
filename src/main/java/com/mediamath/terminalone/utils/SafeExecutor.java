package com.mediamath.terminalone.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;

public class SafeExecutor {
    private static final Logger log = LoggerFactory.getLogger(SafeExecutor.class);
    private static final int DEFAULT_NUMBER_OF_RETRIES = 4;
    // Method will execute TerminalOne query reliably, it will retry call X times
    private <T> T executeSafe(Callable<T> callable, int numberOfLeftRetries, int totalNumberOfRetries) throws Exception {
        T result = null;

        try {
            return callable.call();
        } catch (Exception e) {
            if (numberOfLeftRetries > 1) {
                Thread.sleep(1000 * (totalNumberOfRetries - numberOfLeftRetries + 1));
                return executeSafe(callable, numberOfLeftRetries - 1, totalNumberOfRetries);
            } else {
                throw e;
            }
        }
    }

    public <T> T executeSafe(Callable<T> callable) throws Exception {
        return executeSafe(callable, DEFAULT_NUMBER_OF_RETRIES, DEFAULT_NUMBER_OF_RETRIES);
    }
}
