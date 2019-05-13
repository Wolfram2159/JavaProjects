package com.company.tools;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Converter {
    public static Mat imgToMat(BufferedImage img){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ImageIO.write(img, "jpg", baos);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return Imgcodecs.imdecode(new MatOfByte(baos.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }
    public static BufferedImage matToImg(Mat mat){
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, mob);
        try{
            return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
