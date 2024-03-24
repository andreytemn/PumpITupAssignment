package com.atemnikov.assignment.service;

import com.atemnikov.assignment.model.Item;
import com.atemnikov.assignment.model.OrderRequestParams;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * A service to automate requesting items if they are out of stock.
 */
@AllArgsConstructor
public class OrderAutomationService {
    private final ItemService itemService;
    private final SupplierPurchaseService supplierPurchaseService;

    /**
     * Find the items that match the params and request new ones if some of them are out of stock.
     *
     * @param params the order params
     * @return map of the items and number of them in stock
     */
    @NonNull
    public Map<Item, Integer> findItemsAndOrderIfSomeAreOutOfStock(@NonNull OrderRequestParams params) {
        Map<Item, Integer> items = itemService.findItemInStock(params);
        supplierPurchaseService.orderItemsOutOfStock(items.entrySet()
                .stream()
                .filter(it -> it.getValue() == 0)
                .map(Map.Entry::getKey)
                .toList());
        return items.entrySet()
                .stream()
                .filter(it -> it.getValue() > 0)
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
