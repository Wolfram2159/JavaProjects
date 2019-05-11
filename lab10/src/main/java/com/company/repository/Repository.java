package com.company.repository;

import com.company.exceptions.ImageNotFoundException;
import com.google.gson.JsonObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Repository {
    private Map<Integer, BufferedImage> db;
    private static Integer id;
    public Repository(){
        System.out.println("utworzono db");
        db = new HashMap<>();
        //db.put(1,new BufferedImage(1,1,1));
        id = 0;
    }
    public Integer saveImage(BufferedImage bufferedImage){
        db.put(id,bufferedImage);
        return id++;
    }
    public void deleteImageById(Integer id) throws ImageNotFoundException{
        if (db.remove(id) == null) {
            throw new ImageNotFoundException();
        }
    }
    public BufferedImage getImage(Integer id) throws ImageNotFoundException{
        if (db.get(id) == null) {
            throw new ImageNotFoundException();
        }
        return db.get(id);
    }
}
