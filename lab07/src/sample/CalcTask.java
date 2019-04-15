package sample;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

public class CalcTask extends Task<Void> {
    private Canvas canvas;
    private double points;

    public CalcTask(Canvas canvas, int points) {
        this.canvas = canvas;
        this.points = points;
    }

    @Override
    protected Void call() throws Exception {
        Random rand = new Random();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.setFill(javafx.scene.paint.Color.RED);
        //gc.fillRect(0,0,400,400);
        BufferedImage bi= new BufferedImage((int)canvas.getWidth(), (int)canvas.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        double g = 0;
        for (int k = 0; k < points; k++) {
            double i = -8 + (8 +8) * rand.nextDouble();
            double j = -8 + (8 +8) * rand.nextDouble();
            if(Equation.calc(i,j)){
                g++;
                double Ax = -8;
                double Bx = 8;
                double Cx = -canvas.getWidth()/2;
                double Dx = canvas.getWidth()/2;
                double Ay = -8;
                double By = 8;
                double Cy = -canvas.getHeight()/2;
                double Dy = canvas.getHeight()/2;
                int x = (int)((Dx-Cx)*(i-Ax)/(Bx-Ax)+Cx);
                int y = -(int)((Dy-Cy)*(j-Ay)/(By-Ay)+Cy);
                //System.out.println("i="+i+"/ x="+x);
                bi.setRGB(x+(int)canvas.getWidth()/2, y+(int)canvas.getHeight()/2, Color.YELLOW.getRGB());
            }
        }
        gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0);
        double calk = 256*(g/points);
        System.out.println(calk);
        return null;
    }
}
