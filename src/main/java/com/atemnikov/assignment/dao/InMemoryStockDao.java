package com.atemnikov.assignment.dao;

import com.atemnikov.assignment.model.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryStockDao implements StockDao {
    private final Map<UUID, Integer> stockStorage = new HashMap<>();

    @Override
    public Integer getNumberInStock(UUID id) {
        return stockStorage.get(id);
    }

    @Override
    public void setNumberInStock(UUID id, int number) {
        stockStorage.put(id, number);
    }

    @Override
    public void populate(Map<Item, Integer> itemsInStock) {
        itemsInStock.forEach((item, number) -> setNumberInStock(item.uuid(), number));
    }
}
