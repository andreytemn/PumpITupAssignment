package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.InMemoryAttributeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AttributeServiceTest {

    private AttributeService attributeService;

    @BeforeEach
    void setUp() {
        InMemoryAttributeDao attributeDao = new InMemoryAttributeDao();
        attributeService = new AttributeService(attributeDao);
        attributeDao.populate(TestFixture.ATTRIBUTES);
    }

    @Test
    void testAttributeService() {
        attributeService.validateAttribute(TestFixture.COLOR);
        assertThrows(IllegalArgumentException.class, () -> attributeService.validateAttribute("non-existing"));
    }
}