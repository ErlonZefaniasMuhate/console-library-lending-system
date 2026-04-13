package com.emuhate.library.resources;

import java.security.SecureRandom;
import java.time.Year;

public class IDGenerator {
    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int RANDOM_LENGTH = 4;
    private static final SecureRandom RANDOM = new SecureRandom();

    // Private constructor to prevent instantiation
    private IDGenerator() {}

    /**
     * Generates a compact 8-character ID in the format:
     * YY + NN + RRRR
     * <p>
     * YY   → last two digits of the current year
     * NN   → first two characters of the provided name (uppercase)
     * RRRR → random alphanumeric sequence
     * <p>
     * Example: 26JOA9K2
     *
     * @param name the input name (can be null or empty)
     * @return an 8-character identifier
     */
    public static String generateUUID(String name) {
        String yearPart = getYear();
        String namePart = getName(name);
        String randomPart = generateRandomString(RANDOM_LENGTH);

        return yearPart + namePart + randomPart;
    }

    private static String getYear() {
        return String.valueOf(Year.now().getValue()).substring(2);
    }

    private static String getName(String name) {
        if (name == null || name.isBlank()) {
            return "XX";
        }
        return name.substring(0, Math.min(2, name.length())).toUpperCase();
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
}
