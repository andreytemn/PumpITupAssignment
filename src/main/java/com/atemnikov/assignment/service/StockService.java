package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.StockDao;
import com.atemnikov.assignment.model.Item;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

/**
 * A service that controls the current number of items in stock.
 */
@AllArgsConstructor
public class StockService {
    private final StockDao stockDao;

    /**
     * Get number of given items available in stock.
     * @param item - the requested item
     * @return the number of items in stock or throws IllegalArgumentException if the item is not found.
     */
    public int getNumberOfItemsInStock(@NonNull Item item) {
        return Optional.ofNullable(stockDao.getNumberInStock(item.uuid()))
                .orElseThrow(() -> new IllegalArgumentException("Item " + item + " not found"));
    }

    /**
     * Set the number of items in stock
     * @param item - the item to add
     * @param number - the number of items. Required not to be negative.
     */
    public void setNumberOfItemsInStock(@NonNull Item item, int number) {
        if (number < 0) throw new IllegalArgumentException("Number of items in stock cannot be negative");
        stockDao.setNumberInStock(item.uuid(), number);
    }
}
