package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.lsp.products.Bread;
import ru.job4j.lsp.products.Food;
import ru.job4j.lsp.products.Milk;
import ru.job4j.lsp.stores.Shop;
import ru.job4j.lsp.stores.Store;
import ru.job4j.lsp.stores.Trash;
import ru.job4j.lsp.stores.Warehouse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ControlQualityTest {

    @Test
    public void wasInShopButReallyForWarehouse() {
        Food food = new Milk();
        food.setCreatedDate(LocalDateTime.now());
        food.setExpiredDate(LocalDateTime.now().plusDays(7));
        Store warehouse = new Warehouse();
        Store shop = new Shop(new ArrayList<>(List.of(food)));
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.redistribute();
        assertEquals(1, warehouse.getFoods().size());
    }

    @Test
    public void wasInTrashButReallyForShopWithNoChangeDiscount() {
        Food food = new Milk();
        food.setCreatedDate(LocalDateTime.now().minusDays(3));
        food.setExpiredDate(LocalDateTime.now().plusDays(3));
        food.setDiscount(20);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash(new ArrayList<>(List.of(food)));
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.redistribute();
        assertEquals(1, shop.getFoods().size());
        assertEquals(20, shop.getFoods().get(0).getDiscount());
    }

    @Test
    public void isNewItemForShopWithChangeDiscount() {
        Food food = new Milk();
        food.setCreatedDate(LocalDateTime.now().minusDays(8));
        food.setExpiredDate(LocalDateTime.now().plusDays(1));
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.redistribute(new ArrayList<>(List.of(food)));
        assertEquals(1, shop.getFoods().size());
        assertEquals(50, shop.getFoods().get(0).getDiscount());
    }

    @Test
    public void isNewItemForShopWithNoChangeDiscountAlreadySet() {
        Food food = new Milk();
        food.setCreatedDate(LocalDateTime.now().minusDays(6));
        food.setExpiredDate(LocalDateTime.now().plusDays(1));
        food.setDiscount(30);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.redistribute(new ArrayList<>(List.of(food)));
        assertEquals(1, shop.getFoods().size());
        assertEquals(30, shop.getFoods().get(0).getDiscount());
    }

    @Test
    public void wasInShopReallyForTrashNewItemForTrashWithAddition() {
        Food food1 = new Milk();
        food1.setCreatedDate(LocalDateTime.now().minusDays(10));
        food1.setExpiredDate(LocalDateTime.now().minusDays(6));
        Food food2 = new Bread();
        food2.setCreatedDate(LocalDateTime.now().minusDays(10));
        food2.setExpiredDate(LocalDateTime.now().minusDays(6));
        Store warehouse = new Warehouse();
        Store shop = new Shop(new ArrayList<>(List.of(food1)));
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.redistributeAddition(new ArrayList<>(List.of(food2)));
        assertEquals(2, trash.getFoods().size());
    }
}