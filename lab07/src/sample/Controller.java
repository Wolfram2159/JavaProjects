package sample;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {
    public Canvas canvas;

    @FXML
    public void initialize() {
        System.out.println("second");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        //gc.fillRect(0,0,400,400);

        for (double i = -8; i < 8; i+=0.05) {
            for (double j = -8; j < 8; j+=0.05) {
                System.out.println(Equation.calc(i,j));
                if(Equation.calc(i,j)){
                    gc.setFill(Color.RED);
                    gc.fillRect(i+200,j+200,1,1);
                }else{
                    gc.setFill(Color.BLUE);
                    gc.fillRect(i+200,j+200,1,1);
                }
                /*BufferedImage bi= new BufferedImage(600, 400,
                        BufferedImage.TYPE_INT_RGB);
                Color color = new Color(255,0,0,100);
                bi.setRGB(i, j, );
                gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0
                );*/
            }
        }
    }
}
