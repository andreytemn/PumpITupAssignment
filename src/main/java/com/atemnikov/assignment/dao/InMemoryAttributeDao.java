package com.atemnikov.assignment.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InMemoryAttributeDao implements DummyDao<String> {
    private final Set<String> attributes = new HashSet<>();

    @Override
    public Collection<String> getAll() {
        return Set.copyOf(attributes);
    }

    @Override
    public void populate(Collection<String> attributes) {
        this.attributes.addAll(attributes);
    }

    @Override
    public boolean contains(String name) {
        return attributes.contains(name);
    }
}
