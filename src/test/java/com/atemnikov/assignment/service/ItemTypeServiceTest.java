package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.InMemoryItemTypeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.atemnikov.assignment.service.TestFixture.ATTRIBUTES;
import static com.atemnikov.assignment.service.TestFixture.COLOR;
import static com.atemnikov.assignment.service.TestFixture.ITEM_TYPES;
import static com.atemnikov.assignment.service.TestFixture.SHIRT;
import static com.atemnikov.assignment.service.TestFixture.SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemTypeServiceTest {

    private ItemTypeService itemTypeService;

    @BeforeEach
    void setUp() {
        InMemoryItemTypeDao itemTypeDao = new InMemoryItemTypeDao();
        itemTypeService = new ItemTypeService(itemTypeDao);
        itemTypeDao.populate(ITEM_TYPES);
    }

    @Test
    void testFindByName() {
        assertEquals(SHIRT, itemTypeService.findByName(SHIRT.name()));
    }

    @Test
    void testExceptionThrownIfNameNotFound() {
        assertThrows(IllegalArgumentException.class, () -> itemTypeService.findByName("laptop"));
    }

    @Test
    void testContainsAllAttributes() {
        assertTrue(itemTypeService.checkContainsRequestedAttributes(SHIRT, Set.of(SIZE, COLOR)));
    }

    @Test
    void testContainsSomeAttributes() {
        assertTrue(itemTypeService.checkContainsRequestedAttributes(SHIRT, Set.of(SIZE)));
    }

    @Test
    void testTrueIfNoAttributesProvided() {
        assertTrue(itemTypeService.checkContainsRequestedAttributes(SHIRT, Set.of()));
    }

    @Test
    void testFalseForTooMuchAttributes() {
        assertFalse(itemTypeService.checkContainsRequestedAttributes(SHIRT, ATTRIBUTES));
    }
}