package com.wolfram.lab13;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * @author Wolfram
 * @date 2019-05-27
 */
public class Accelerometer implements SensorEventListener {

    private AnimationCallback animationCallback;

    public Accelerometer(AnimationCallback animationCallback) {
        this.animationCallback = animationCallback;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        animationCallback.animate(event.values[0], event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
