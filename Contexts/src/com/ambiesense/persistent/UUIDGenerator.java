package com.ambiesense.persistent;

import java.util.UUID;

public class UUIDGenerator {
    public static String createId() {
        UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }
}