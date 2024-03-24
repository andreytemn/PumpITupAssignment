package com.atemnikov.assignment.service;

import com.atemnikov.assignment.model.Category;
import com.atemnikov.assignment.model.Item;
import com.atemnikov.assignment.model.ItemType;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TestFixture {

    // Categories
    public static final Category CLOTHES = new Category(UUID.randomUUID(), "clothes");
    public static final Category FOOD = new Category(UUID.randomUUID(), "food");

    // Attributes
    public static final String SIZE = "size";
    public static final String COLOR = "color";
    public static final String SEASON = "season";
    public static final String TASTE = "taste";

    public static final Set<String> ATTRIBUTES = Set.of(SIZE, COLOR, SEASON, TASTE);

    // Item types
    public static final ItemType SHIRT = new ItemType(UUID.randomUUID(), CLOTHES, "shirt", Set.of(SIZE, COLOR));
    public static final ItemType SOCKS = new ItemType(UUID.randomUUID(), CLOTHES, "socks", Set.of(SIZE, COLOR, SEASON));
    public static final ItemType APPLES = new ItemType(UUID.randomUUID(), FOOD, "apples", Set.of(TASTE));

    public static final Set<ItemType> ITEM_TYPES = Set.of(SHIRT, SOCKS, APPLES);

    //Items
    public static final Item ROCK_T_SHIRT_FOR_CHILDREN = new Item(UUID.randomUUID(), SHIRT, "Rock t-shirt",
            Map.of(SIZE, "S", COLOR, "black"));
    public static final Item BUSINESS_CASUAL_SHIRT_L = new Item(UUID.randomUUID(), SHIRT, "Business casual shirt L",
            Map.of(SIZE, "L", COLOR, "white"));
    public static final Item BUSINESS_CASUAL_SHIRT_XL = new Item(UUID.randomUUID(), SHIRT, "Business casual shirt XL",
            Map.of(SIZE, "XL", COLOR, "white"));

    public static final Item PARTY_SHIRT_L = new Item(UUID.randomUUID(), SHIRT, "Party shirt",
            Map.of(SIZE, "L", COLOR, "orange"));
    public static final Item NEW_YEAR_SOCKS = new Item(UUID.randomUUID(), SOCKS, "New year socks", Map.of(SIZE, "M",
            COLOR, "with Santa", SEASON, "winter"));
    public static final Item HIKING_SOCKS = new Item(UUID.randomUUID(), SOCKS, "Hiking socks", Map.of(SIZE, "L",
            COLOR, "black", SEASON, "all"));

    public static final Item SWEET_APPLES = new Item(UUID.randomUUID(), APPLES, "Sweet apples", Map.of(TASTE, "sweet"));

    public static final Set<Item> ITEMS = Set.of(ROCK_T_SHIRT_FOR_CHILDREN, BUSINESS_CASUAL_SHIRT_L,
            BUSINESS_CASUAL_SHIRT_XL, PARTY_SHIRT_L, NEW_YEAR_SOCKS, HIKING_SOCKS, SWEET_APPLES);

    public static final Map<Item, Integer> ITEMS_IN_STOCK = Map.of(
            BUSINESS_CASUAL_SHIRT_L, 1,
            BUSINESS_CASUAL_SHIRT_XL, 0,
            PARTY_SHIRT_L, 10,
            ROCK_T_SHIRT_FOR_CHILDREN, 2,
            NEW_YEAR_SOCKS, 100,
            HIKING_SOCKS, 42,
            SWEET_APPLES, 50);
}
