package sample;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    public Canvas canvas;
    public TextField textField;
    public Button start;
    public Button stop;
    public ProgressBar progressBar;

    @FXML
    public void initialize() {
        //System.out.println("second");
        /*GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.setFill(javafx.scene.paint.Color.RED);
        //gc.fillRect(0,0,400,400);
        BufferedImage bi= new BufferedImage((int)canvas.getWidth(), (int)canvas.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (double i = -8; i < 8; i+=0.01) {
            for (double j = -8; j < 8; j+=0.01) {
                //System.out.println(Equation.calc(i,j));
                if(Equation.calc(i,j)){
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
                    System.out.println("i="+i+"/ x="+x);
                    bi.setRGB(x+(int)canvas.getWidth()/2, y+(int)canvas.getHeight()/2, Color.YELLOW.getRGB());
                }
            }
        }
        gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0
        );*/
    }

    public void startClick(MouseEvent mouseEvent) {
        CalcTask task = new CalcTask(canvas, Integer.parseInt(textField.getText()));
        new Thread(task).start();
    }

    public void stopClick(MouseEvent mouseEvent) {

    }
}
