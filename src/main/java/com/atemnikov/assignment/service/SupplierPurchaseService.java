package com.atemnikov.assignment.service;

import com.atemnikov.assignment.model.Item;
import lombok.NonNull;

import java.util.Collection;

/**
 * A service that sends the requests to the Item suppliers to top up the stock.
 */
public class SupplierPurchaseService {
    /**
     * Send the request to the item suppliers. Current implementation prints the items to be ordered
     * @param items - items to be ordered.
     */
    public void orderItemsOutOfStock(@NonNull Collection<Item> items) {
        if (items.isEmpty()) return;
        System.out.println("Elements to be ordered from the supplier:");
        items.forEach(System.out::println);
    }
}
