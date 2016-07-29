package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ShakeStartActivity extends AppCompatActivity implements SensorEventListener {

    private ImageButton shakeButton;
    private TextView title_tap;
    Context myContext;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private boolean shakeIntentStarted = false;
    RotateAnimation ra = new RotateAnimation(0, 60, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    private int significantShakeMag = 25; // Magnitude that needs to be exceeded before shaking starts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shake_start);
        myContext = this;
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        shakeIntentStarted = false;
        ra.setFillAfter(true);
        ra.setDuration(1000);
        ra.setRepeatMode(Animation.REVERSE);
        ra.setRepeatCount(Animation.INFINITE);
        shakeButton.startAnimation(ra);
    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        shakeButton = (ImageButton) findViewById(R.id.button_shake);
        title_tap = (TextView) findViewById(R.id.title_tap);
        Typeface fontLight = Typeface.createFromAsset(getAssets(), "TheSansExtraLight-Plain.ttf");
        title_tap.setTypeface(fontLight);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        Double shakeMagnitude = Math.sqrt(x * x + y * y + z * z);

        if (shakeMagnitude > significantShakeMag && shakeIntentStarted == false) {
            shakeIntentStarted = true;
            ra.cancel();
            ra.reset();
            shakeButton.setRotation(-30);
            Intent shakingIntent = new Intent(myContext, ShakingActivity.class);
            startActivity(shakingIntent);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
