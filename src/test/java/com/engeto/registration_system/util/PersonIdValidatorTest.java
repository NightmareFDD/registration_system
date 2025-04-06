package com.engeto.registration_system.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonIdValidatorTest {

    @Test
    void validId_returnsTrue() {
        String validId = "jXa4g3H7oPq2";
        assertTrue(PersonIdValidator.isValid(validId));
    }

    @Test
    void invalidId_returnsFalse() {
        String invalidId = "000000000000";
        assertFalse(PersonIdValidator.isValid(invalidId));
    }
}