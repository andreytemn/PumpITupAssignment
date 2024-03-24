package com.atemnikov.assignment.service;

import com.atemnikov.assignment.dao.InMemoryAttributeDao;
import com.atemnikov.assignment.dao.InMemoryItemDao;
import com.atemnikov.assignment.dao.InMemoryItemTypeDao;
import com.atemnikov.assignment.dao.InMemoryStockDao;
import com.atemnikov.assignment.model.OrderRequestParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static com.atemnikov.assignment.service.TestFixture.BUSINESS_CASUAL_SHIRT_L;
import static com.atemnikov.assignment.service.TestFixture.BUSINESS_CASUAL_SHIRT_XL;
import static com.atemnikov.assignment.service.TestFixture.ITEMS;
import static com.atemnikov.assignment.service.TestFixture.ITEMS_IN_STOCK;
import static com.atemnikov.assignment.service.TestFixture.ITEM_TYPES;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderAutomationServiceTest {

    private SupplierPurchaseService supplierPurchaseService;
    private OrderAutomationService orderAutomationService;

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
        ItemService itemService = new ItemService(attributeService, itemTypeService, stockService, itemDao);
        itemDao.populate(ITEMS);

        supplierPurchaseService = Mockito.spy(new SupplierPurchaseService());
        orderAutomationService = new OrderAutomationService(itemService, supplierPurchaseService);
    }

    @Test
    void testItemsRequestedWhenOutOfStock() {
        assertEquals(Map.of(BUSINESS_CASUAL_SHIRT_L, 1),
                orderAutomationService.findItemsAndOrderIfSomeAreOutOfStock(new OrderRequestParams("shirt",
                        Map.of(
                                "color", "white"))));
        Mockito.verify(supplierPurchaseService).orderItemsOutOfStock(List.of(BUSINESS_CASUAL_SHIRT_XL));

    }
}