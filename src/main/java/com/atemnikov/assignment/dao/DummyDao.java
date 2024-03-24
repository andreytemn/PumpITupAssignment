package com.atemnikov.assignment.dao;

import java.util.Collection;
import java.util.UUID;

public interface DummyDao<T> {
    Collection<T> getAll();

    void populate(Collection<T> values);

    boolean contains(String name);
}
