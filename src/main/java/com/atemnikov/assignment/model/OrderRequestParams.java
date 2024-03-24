package com.atemnikov.assignment.model;

import java.util.Map;

public record OrderRequestParams(String itemTypeName, Map<String, String> attributes) {
}
