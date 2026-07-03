package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestAssertions {

    @Test
    public void testAssert() {

        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = null;

        // Checks if both strings are equal
        assertEquals(s1, s2);

        // Checks if the object is null
        assertNull(s3);

        // Checks if the object is not null
        assertNotNull(s2);

        // Checks if the condition is true
        assertTrue(s1.equals(s2));

        // Checks if the condition is false
        assertFalse(s1.isEmpty());

        // Checks if an exception is thrown
        assertThrows(ArithmeticException.class, () -> {
            int x = 10 / 0;
        });
    }
}