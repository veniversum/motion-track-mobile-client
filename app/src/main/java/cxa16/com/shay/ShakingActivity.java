package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ShakingActivity extends AppCompatActivity {

    Context myContext;
    private TextView mTitle;
    private TextView mInstructions;
    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        setContentView(R.layout.activity_shaking);
        mTitle = (TextView) findViewById(R.id.title_wallet);
        mInstructions = (TextView) findViewById(R.id.shaking_instruction);

        i = 0;
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setProgress(i);
        mCountDownTimer=new CountDownTimer(3000,29) {

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
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Typeface fontLight = Typeface.createFromAsset(getAssets(), "TheSansExtraLight-Plain.ttf");
        mTitle.setTypeface(fontLight);
        mInstructions.setTypeface(fontLight);
    }

}
