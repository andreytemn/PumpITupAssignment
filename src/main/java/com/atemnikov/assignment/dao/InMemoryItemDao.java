package com.atemnikov.assignment.dao;

import com.atemnikov.assignment.model.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryItemDao implements DummyDao<Item> {

    private final Map<String, Item> itemStorage = new HashMap<>();

    @Override
    public Collection<Item> getAll() {
        return itemStorage.values();
    }

    @Override
    public void populate(Collection<Item> values) {
        values.forEach(it -> itemStorage.put(it.name(), it));
    }

    @Override
    public boolean contains(String name) {
        return itemStorage.containsKey(name);
    }
}
