package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.DummyDao;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * A service that validates the attributes of the order.
 */
@AllArgsConstructor
public class AttributeService {
    private final DummyDao<String> attributeDao;

    /**
     * Validate the attribute by name and throw an IllegalArgumentException if it is not defined.
     *
     * @param name the name of the argument to validate
     */
    public void validateAttribute(@NonNull String name) {
        if (!attributeDao.contains(name)) throw new IllegalArgumentException("Attribute " + name + " not defined");
    }
}
