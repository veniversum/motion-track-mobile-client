package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import cxa16.com.shay.handlers.ReceiverTransaction;
import cxa16.com.shay.handlers.SenderTransaction;
import cxa16.com.shay.handlers.Transaction;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ShakingActivity extends AppCompatActivity implements SensorEventListener{

    Context myContext;
    private TextView mTitle;
    private TextView mInstructions;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;
    private float[] data = new float[100];
    private int datai;
    private int i;
    private GlobalVars globalVariable;
    private boolean isSender;
    private float amount;
    private boolean isSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalVariable = (GlobalVars) getApplicationContext();
        isSender = globalVariable.getSender();

        myContext = this;
        setContentView(R.layout.activity_shaking);
        mTitle = (TextView) findViewById(R.id.title_wallet);
        mInstructions = (TextView) findViewById(R.id.shaking_instruction);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        i = 0;
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setProgress(i);
        mCountDownTimer=new CountDownTimer(5000,29) {

            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                mProgressBar.setProgress(i);

            }

            @Override
            public void onFinish() {
                //Do what you want

                i++;
                mProgressBar.setProgress(i);
                mTitle.setText("Awaiting Server Response");
                Log.i("a", Arrays.toString(data));
                Transaction transaction = null;
                if (!isSent) {
                    if (isSender) {
                        transaction = new SenderTransaction("test_sender_1234", amount, data, new Transaction.TransactionEventListener() {
                            @Override
                            public void onSuccess(Transaction t, float amount, String other) {
                                Toast.makeText(myContext, "Sent to " + other + " $" + amount, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Transaction t) {
                                Toast.makeText(myContext, "Error, unable to process payment", Toast.LENGTH_LONG).show();
                            }
                        });

                    } else {
                        transaction = new ReceiverTransaction("test_receiver_1234", data, new Transaction.TransactionEventListener() {
                            @Override
                            public void onSuccess(Transaction t, float amount, String other) {
                                Toast.makeText(myContext, "Received from " + other + " $" + amount, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Transaction t) {
                                Toast.makeText(myContext, "Error, unable to process payment", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    transaction.setLatLng(1.296049f, 103.849409f);
                    transaction.execute();
                    isSent = true;
                }
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void onResume() {
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        super.onResume();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Typeface fontLight = Typeface.createFromAsset(getAssets(), "TheSansExtraLight-Plain.ttf");
        mTitle.setTypeface(fontLight);
        mInstructions.setTypeface(fontLight);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        Float shakeMagnitude = (float)Math.sqrt(x * x + y * y + z * z);

        if (datai < data.length-1) data[datai++] = shakeMagnitude;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
