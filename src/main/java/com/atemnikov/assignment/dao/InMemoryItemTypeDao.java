package com.atemnikov.assignment.dao;

import com.atemnikov.assignment.model.ItemType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryItemTypeDao implements DummyDao<ItemType> {
    private final Map<String, ItemType> itemTypeStorage = new HashMap<>();

    @Override
    public Collection<ItemType> getAll() {
        return itemTypeStorage.values();
    }

    @Override
    public void populate(Collection<ItemType> items) {
        items.forEach(it -> itemTypeStorage.put(it.name(), it));
    }

    @Override
    public boolean contains(String name) {
        return itemTypeStorage.containsKey(name);
    }
}
