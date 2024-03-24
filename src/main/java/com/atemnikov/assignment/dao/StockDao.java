package com.atemnikov.assignment.dao;

import com.atemnikov.assignment.model.Item;

import java.util.Map;
import java.util.UUID;

public interface StockDao {
    Integer getNumberInStock(UUID id);

    void setNumberInStock(UUID id, int number);

    void populate(Map<Item, Integer> itemsInStock);
}
