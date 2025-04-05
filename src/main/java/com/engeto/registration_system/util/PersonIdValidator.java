package com.engeto.registration_system.util;

import com.engeto.registration_system.exception.ValidationRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class PersonIdValidator {
    private static final Set<String> allowedIds = new HashSet<>();

    static {
        InputStream is = PersonIdValidator.class.getClassLoader().getResourceAsStream("dataPersonId.txt");
        if (is == null) {
            log.error("Resource file dataPersonId.txt not found");
            throw new ValidationRuntimeException("Resource file dataPersonId.txt not found");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allowedIds.add(line.trim());
            }
            log.info("Loaded {} allowed personIDs from resource file", allowedIds.size());
        } catch (IOException e) {
            log.error("Failed to load person ID list from resource", e);
            throw new ValidationRuntimeException("Failed to load person ID list");
        }
    }

    public static boolean isValid(String personId) {
        boolean result = allowedIds.contains(personId);
        log.debug("Validating personID '{}': {}", personId, result);
        return result;
    }

    private PersonIdValidator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
