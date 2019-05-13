package com.company;

import com.company.exceptions.ImageNotFoundException;
import com.company.repository.Repository;
import com.google.gson.JsonObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    private Repository repository = new Repository();

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // I) Post /image todo: working
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String addImage(HttpServletRequest requestEntity) throws IOException {
        InputStream is = requestEntity.getInputStream();
        BufferedImage bi = ImageIO.read(is);
        JsonObject json = new JsonObject();
        Integer id = repository.saveImage(bi);
        json.addProperty("id", id);
        json.addProperty("width", bi.getWidth());
        json.addProperty("height", bi.getHeight());
        return json.toString();
    }

    @RequestMapping(value = "/image/show/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] showImage(@PathVariable Integer id) throws ImageNotFoundException, IOException {
        BufferedImage bi = repository.getImage(id);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", outputStream);
        return outputStream.toByteArray();
    }

    //II) DELETE /image/{id} todo: working
    @RequestMapping("/image/{id}")
    public ResponseEntity<String> deleteImageById(@PathVariable Integer id) {
        try {
            repository.deleteImageById(id);
            return new ResponseEntity<>("Deleted succcessfuly", HttpStatus.OK);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //III) GET /image/{id}/size todo:working
    @RequestMapping("/image/{id}/size")
    public ResponseEntity<String> getImageSize(@PathVariable Integer id) {
        BufferedImage bi = null;
        try {
            bi = repository.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("width", bi.getWidth());
        jsonObject.addProperty("height", bi.getHeight());
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);

    }

    // IV) GET /image/{id}/scale/{percent} todo: working
    @RequestMapping(value = "/image/{id}/scale/{percent}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getScaleImage(@PathVariable Integer id, @PathVariable Double percent) throws IOException {
        BufferedImage before = null;
        try {
            before = repository.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (percent <= 0 && percent >=100){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int w = (int) (before.getWidth() * (percent / 100));
        int h = (int) (before.getHeight() * (percent / 100));
        BufferedImage after = new BufferedImage(w, h, before.getType());
        AffineTransform at = new AffineTransform();
        at.scale(percent / 100, percent / 100);
        AffineTransformOp atOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        after = atOp.filter(before, after);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(after, "jpg", bos);
        return new ResponseEntity<>(bos.toByteArray(), HttpStatus.OK);
    }

    // V) GET /image/{id}/histogram todo:working
    @RequestMapping("/image/{id}/histogram")
    public ResponseEntity<String> getImageHistogram(@PathVariable Integer id) {
        BufferedImage image = null;
        try {
            image = repository.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int[] red = new int[256];
        int[] green = new int[256];
        int[] blue = new int[256];

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color c = new Color(image.getRGB(i, j));
                red[c.getRed()]++;
                green[c.getGreen()]++;
                blue[c.getBlue()]++;
            }
        }
        JsonObject jsonRed = new JsonObject();
        JsonObject jsonGreen = new JsonObject();
        JsonObject jsonBlue = new JsonObject();

        for (int i = 0; i < 255; i++) {
            jsonRed.addProperty("" + i + "", red[i]);
            jsonGreen.addProperty("" + i + "", green[i]);
            jsonBlue.addProperty("" + i + "", blue[i]);
        }
        JsonObject jsonRGB = new JsonObject();
        jsonRGB.add("R", jsonRed);
        jsonRGB.add("G", jsonGreen);
        jsonRGB.add("B", jsonBlue);
        return new ResponseEntity<>(jsonRGB.toString(), HttpStatus.OK);
    }

    // VI) GET /image/{id}/crop/{start}/{stop}/{width}/{height} todo:working
    @RequestMapping(value = "/image/{id}/crop/{startX}/{startY}/{width}/{height}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageFragment(@PathVariable Integer id, @PathVariable Integer startX, @PathVariable Integer startY,
                                   @PathVariable Integer width, @PathVariable Integer height) throws IOException {
        BufferedImage bi = null;
        try {
            bi = repository.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BufferedImage newImage = new BufferedImage(width, height, bi.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newImage.setRGB(i, j, bi.getRGB(startX + i, startY + j));
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(newImage, "jpg", bos);
        return new ResponseEntity<>(bos.toByteArray(), HttpStatus.OK);
    }

    // VII) GET /image/{id}/greyscale todo:working
    @RequestMapping(value = "/image/{id}/greyscale", method = RequestMethod.GET, produces =
            MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageGrey(@PathVariable Integer id) throws IOException {
        BufferedImage bImage = null;
        try {
            bImage = repository.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (int i = 0; i < bImage.getHeight(); i++) {
            for (int j = 0; j < bImage.getWidth(); j++) {
                Color c = new Color(bImage.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue,

                        red + green + blue, red + green + blue);

                bImage.setRGB(j, i, newColor.getRGB());
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return new ResponseEntity<>(bos.toByteArray(), HttpStatus.OK);
    }

    // VIII) GET /image/{id}/blur/{radius} todo:working
    @RequestMapping(value = "/image/{id}/blur/{radius}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageBlur(@PathVariable Integer id, @PathVariable Integer radius) throws IOException {
        BufferedImage image = null;
        try {
            image = repository.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (radius <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int rad2 = radius * radius;
        float[] matrix = new float[rad2];
        for (int i = 0; i < rad2; i++)
            matrix[i] = 1.0f / (float) rad2;
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        BufferedImageOp op = new ConvolveOp(new Kernel(radius, radius, matrix), ConvolveOp.EDGE_NO_OP, null);
        BufferedImage blurredImage = op.filter(image, newImage);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(blurredImage, "jpg", bos);
        return new ResponseEntity<>(bos.toByteArray(), HttpStatus.OK);
    }
}
