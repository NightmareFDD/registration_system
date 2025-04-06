package com.engeto.registration_system.util;

import com.engeto.registration_system.exception.ValidationRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for validating person ID values loaded from a predefined resource file.
 * <p>
 * The class loads a set of allowed person IDs from a file on classpath (dataPersonId.txt)
 * and provides a method to validate whether a given ID is in the list.
 * </p>
 * This class is non-instantiable.
 */

@Slf4j
public class PersonIdValidator {

    private static final String FILE_NAME = "dataPersonId.txt";
    private static final Set<String> allowedIds = loadPersonIds();

    private PersonIdValidator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Loads person IDs from the resource file and returns them as a Set.
     *
     * @return set of allowed person ID values
     * @throws ValidationRuntimeException if reading the file fails
     */
    private static Set<String> loadPersonIds() {
        try (BufferedReader reader = getReader()) {
            Set<String> ids = extractValidLines(reader);
            log.info("Loaded {} allowed personIDs from '{}'", ids.size(), FILE_NAME);
            return ids;
        } catch (Exception e) {
            throw handleLoadFailure(e);
        }
    }

    /**
     * Creates a {@link BufferedReader} for the person ID resource file.
     *
     * @return a BufferedReader for the file
     * @throws NullPointerException if the file is not found
     */
    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(
                        PersonIdValidator.class.getClassLoader().getResourceAsStream(FILE_NAME),
                        "Resource file " + FILE_NAME + " not found"
                )));
    }

    /**
     * Extracts non-empty, trimmed lines from the given BufferedReader.
     *
     * @param reader BufferedReader from which lines are read
     * @return a set of valid (non-empty, trimmed) lines
     */
    private static Set<String> extractValidLines(BufferedReader reader) {
        return reader.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toSet());
    }

    /**
     * Handles exceptions during resource loading by logging and wrapping into a runtime exception.
     *
     * @param e the caught exception
     * @return wrapped {@link ValidationRuntimeException}
     */
    private static ValidationRuntimeException handleLoadFailure(Exception e) {
        log.error("Failed to load person ID list from '{}'", FILE_NAME, e);
        return new ValidationRuntimeException("Failed to load person ID list");
    }

    /**
     * Validates whether the given person ID is present in the allowed list.
     *
     * @param personId person ID string to validate
     * @return {@code true} if the person ID is valid; {@code false} otherwise
     */
    public static boolean isValid(String personId) {
        boolean valid = allowedIds.contains(personId);
        logValidation(personId, valid);
        return valid;
    }

    /**
     * Logs the result of person ID validation attempt.
     *
     * @param personId the person ID that was validated
     * @param valid {@code true} if valid, {@code false} otherwise
     */
    private static void logValidation(String personId, boolean valid) {
        if (valid) {
            log.debug("Valid personID '{}'", personId);
        } else {
            log.warn("Invalid personID attempted: '{}'", personId);
        }
    }
}