package com.atemnikov.assignment.model;

import java.util.Set;
import java.util.UUID;

public record ItemType(UUID uuid, Category category, String name, Set<String> attributes) {
}
