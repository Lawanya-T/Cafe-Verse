
package com.cafeverse.view;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class cart {

     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
    public static class Item {
        public String name;
        public double price;
        public String imagePath;
        public int quantity;

        public Item(String name, double price, String imagePath) {
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
        }
    }

    private static ArrayList<Item> items = new ArrayList<>();

    // ✅ Use this method to add items (with image path)
    public static void addItem(String name, double price, String imagePath) {
        items.add(new Item(name, price, imagePath));
    }

    // ✅ Optional overload (if needed elsewhere)
    public static void addItem(Item item) {
        items.add(item);
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }

    public static void clearCart() {
        items.clear();
    }

    public static Item[] getItems() {
        return items.toArray(new Item[0]);
    }

    public static double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.price;
        }
        return total;
    }
}
