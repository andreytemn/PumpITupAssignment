package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.DummyDao;
import com.atemnikov.assignment.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collection;

/**
 * A service to manage the item types.
 */
@AllArgsConstructor
public class ItemTypeService {
    private final DummyDao<ItemType> dao;

    /**
     * Find the ItemType by its name.
     *
     * @param name the name of the ItemType
     * @return the ItemType or throw an IllegalArgumentException if not found
     */
    @NonNull
    public ItemType findByName(@NonNull String name) {
        return dao.getAll()
                .stream()
                .filter(it -> it.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item type for name " + name + " not found"));
    }

    /**
     * Check that the ItemType has all the attributes defined in the order params.
     *
     * @param itemType   the ItemType to check
     * @param attributes the attributes of the order
     * @return true if the ItemType contains all the attributes or if the collection is empty
     */
    public boolean checkContainsRequestedAttributes(@NonNull ItemType itemType,
                                                    @NonNull Collection<String> attributes) {
        return itemType.attributes().containsAll(attributes);
    }
}
