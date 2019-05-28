package com.wolfram.lab13;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private float x = 0;
    private float y = 0;

    private int width;
    private int height;

    private float dirX = 1;
    private float dirY = 1;

    private Runnable run;

    @BindView(R.id.ball)
    ImageView ball;

    @BindView(R.id.layout)
    ConstraintLayout layout;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = layout.getWidth();
        height = layout.getHeight();
        height -= ball.getHeight();
        width -= ball.getWidth();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Handler handler = new Handler();
        run = () -> {
            if (ball.getX() > width) {
                dirX = -1;
            } else if (ball.getY() > height) {
                dirY = -1;
            } else if (ball.getX() < 0) {
                dirX = 1;
            } else if (ball.getY() < 0) {
                dirY = 1;
            }
            ball.setX(ball.getX() + x * dirX * 10);
            ball.setY(ball.getY() + y * dirY * 10);

            handler.postDelayed(run, 50);
        };
        handler.postDelayed(run, 50);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(new Accelerometer((x, y) -> {
            this.x = x;
            this.y = y;
        }), sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
