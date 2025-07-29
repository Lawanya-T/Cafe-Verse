package com.cafeverse.view;

import javafx.scene.image.Image;

public class DecorationItem {
    private String name;
    private Image image;

    public DecorationItem(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    public String getName() { return name; }
    public Image getImage() { return image; }
}