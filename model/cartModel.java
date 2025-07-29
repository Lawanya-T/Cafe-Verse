// package com.cafeverse.model;

// public class cartModel {
//     static String username;
//     static String name;
//     static int quantity;
//     static double price;
//     public static String getUsername() {
//         return username;
//     }
//     public void setUsername(String username) {
//         this.username = username;
//     }
//     public static String getName() {
//         return name;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public static int getQuantity() {
//         return quantity;
//     }
//     public void setQuantity(int quantity) {
//         this.quantity = quantity;
//     }
//     public static double getPrice() {
//         return price;
//     }
//     public void setPrice(double price) {
//         this.price = price;
//     }

    
// }

package com.cafeverse.model;

public class cartModel {
    private static String username;
    private static String name;
    private static int quantity;
    private static double price;

    public cartModel(String username, String name, int quantity, double price) {
        this.username = username;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static String getUsername() { try {
        return username;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return name; }
    public static String getName() { return name; }
    public static int getQuantity() { return quantity; }
    public static double getPrice() { return price; }
}
