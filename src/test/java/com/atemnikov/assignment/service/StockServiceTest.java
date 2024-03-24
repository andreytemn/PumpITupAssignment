package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.InMemoryStockDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StockServiceTest {
    private StockService stockService;

    @BeforeEach
    void setUp() {
        stockService = new StockService(new InMemoryStockDao());
    }

    @Test
    void testStoresData() {
        stockService.setNumberOfItemsInStock(TestFixture.NEW_YEAR_SOCKS, 10);
        assertEquals(stockService.getNumberOfItemsInStock(TestFixture.NEW_YEAR_SOCKS), 10);
    }

    @Test
    void testWorksWithZeros() {
        stockService.setNumberOfItemsInStock(TestFixture.NEW_YEAR_SOCKS, 0);
        assertEquals(stockService.getNumberOfItemsInStock(TestFixture.NEW_YEAR_SOCKS), 0);
    }

    @Test
    void testThrowsExceptionIfItemNotDefined() {
        assertThrows(IllegalArgumentException.class,
                () -> stockService.getNumberOfItemsInStock(TestFixture.NEW_YEAR_SOCKS));
    }

    @Test
    void testThrowsExceptionForNegativeNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> stockService.setNumberOfItemsInStock(TestFixture.NEW_YEAR_SOCKS, -1));
    }
}