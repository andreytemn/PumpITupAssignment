package com.atemnikov.assignment.model;


import java.util.Map;
import java.util.UUID;

public record Item(UUID uuid, ItemType itemType, String name, Map<String, String> attributeValues) {
}
