package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.InMemoryAttributeDao;
import com.atemnikov.assignment.dao.InMemoryItemDao;
import com.atemnikov.assignment.dao.InMemoryItemTypeDao;
import com.atemnikov.assignment.dao.InMemoryStockDao;
import com.atemnikov.assignment.model.OrderRequestParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.atemnikov.assignment.service.TestFixture.BUSINESS_CASUAL_SHIRT_L;
import static com.atemnikov.assignment.service.TestFixture.BUSINESS_CASUAL_SHIRT_XL;
import static com.atemnikov.assignment.service.TestFixture.HIKING_SOCKS;
import static com.atemnikov.assignment.service.TestFixture.ITEMS;
import static com.atemnikov.assignment.service.TestFixture.ITEMS_IN_STOCK;
import static com.atemnikov.assignment.service.TestFixture.ITEM_TYPES;
import static com.atemnikov.assignment.service.TestFixture.NEW_YEAR_SOCKS;
import static com.atemnikov.assignment.service.TestFixture.PARTY_SHIRT_L;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemServiceTest {
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        InMemoryItemTypeDao itemTypeDao = new InMemoryItemTypeDao();
        ItemTypeService itemTypeService = new ItemTypeService(itemTypeDao);
        itemTypeDao.populate(ITEM_TYPES);

        InMemoryStockDao stockDao = new InMemoryStockDao();
        StockService stockService = new StockService(stockDao);
        stockDao.populate(ITEMS_IN_STOCK);


        InMemoryAttributeDao attributeDao = new InMemoryAttributeDao();
        AttributeService attributeService = new AttributeService(attributeDao);
        attributeDao.populate(TestFixture.ATTRIBUTES);

        InMemoryItemDao itemDao = new InMemoryItemDao();
        itemService = new ItemService(attributeService, itemTypeService, stockService, itemDao);
        itemDao.populate(ITEMS);
    }


    @Test
    void testPositiveCase() {
        assertEquals(Map.of(BUSINESS_CASUAL_SHIRT_L, 1), itemService.findItemInStock(new OrderRequestParams("shirt",
                Map.of(
                        "size", "L",
                        "color", "white"))));
    }

    @Test
    void testFindLargeShirts() {
        assertEquals(Map.of(BUSINESS_CASUAL_SHIRT_L, 1, PARTY_SHIRT_L, 10),
                itemService.findItemInStock(new OrderRequestParams("shirt",
                        Map.of(
                                "size", "L"))));
    }

    @Test
    void testFindAllSocks() {
        assertEquals(Map.of(NEW_YEAR_SOCKS, 100, HIKING_SOCKS, 42),
                itemService.findItemInStock(new OrderRequestParams("socks",
                        Map.of())));
    }

    @Test
    void testFailsForApplesWithSize() {
        assertThrows(IllegalArgumentException.class, () -> itemService.findItemInStock(new OrderRequestParams(
                "apples", Map.of("size", "L"))));
    }

    @Test
    void testFailsForMisspelledParam() {
        assertThrows(IllegalArgumentException.class, () -> itemService.findItemInStock(new OrderRequestParams(
                "apples", Map.of("asdf", "wer"))));
    }

    @Test
    void testReturnsZeroForItemOutOfStock() {
        assertEquals(Map.of(BUSINESS_CASUAL_SHIRT_XL, 0), itemService.findItemInStock(new OrderRequestParams("shirt",
                Map.of(
                        "size", "XL",
                        "color", "white"))));
    }

    @Test
    void testReturnsEmptyForMismatch() {
        assertEquals(Map.of(), itemService.findItemInStock(new OrderRequestParams("shirt",
                Map.of(
                        "size", "L",
                        "color", "black"))));
    }
}