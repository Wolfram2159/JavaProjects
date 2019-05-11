package com.company.repository;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Image {

    private BufferedImage image;

    public Image(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
    public int getHeight(){
        return image.getHeight();
    }
    public int getWidth(){
        return image.getWidth();
    }
    public int getRGB(int i, int j){
        return image.getRGB(i,j);
    }
}
