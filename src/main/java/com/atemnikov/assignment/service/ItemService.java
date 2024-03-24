package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.DummyDao;
import com.atemnikov.assignment.model.Item;
import com.atemnikov.assignment.model.ItemType;
import com.atemnikov.assignment.model.OrderRequestParams;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A service that applies lookup operations for Item.
 */
@AllArgsConstructor
public class ItemService {
    private final AttributeService attributeService;
    private final ItemTypeService itemTypeService;
    private final StockService stockService;

    private final DummyDao<Item> itemDao;

    /**
     * Find the items in stock that match the given request params. Throw IllegalArgumentException if the attributes of
     * the params don't match the ones specified for the ItemType.
     *
     * @param params parameters of the order
     * @return map of items and how many of them are in stock
     */
    @NonNull
    public Map<Item, Integer> findItemInStock(@NonNull OrderRequestParams params) {
        ItemType itemType = itemTypeService.findByName(params.itemTypeName());
        Map<String, String> attributes = params.attributes();
        attributes.keySet().forEach(attributeService::validateAttribute);

        if (!itemTypeService.checkContainsRequestedAttributes(itemType, attributes.keySet())) {
            throw new IllegalArgumentException("Set of attributes does not match item type" + params.itemTypeName());
        }
        return findMatchingItems(
                itemDao.getAll()
                        .stream()
                        .filter(it -> it.itemType().equals(itemType))
                        .toList(), attributes)
                .stream()
                .collect(Collectors.toMap(Function.identity(), stockService::getNumberOfItemsInStock));
    }

    private List<Item> findMatchingItems(List<Item> list, Map<String, String> attributes) {
        return list.stream().filter(it -> matchAttributes(it, attributes)).toList();
    }

    private boolean matchAttributes(Item item, Map<String, String> attributes) {
        return attributes.entrySet()
                .stream()
                .allMatch(attr -> item.attributeValues().get(attr.getKey()).equalsIgnoreCase(attr.getValue()));
    }
}
