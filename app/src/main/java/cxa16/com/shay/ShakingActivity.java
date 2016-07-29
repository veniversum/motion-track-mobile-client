package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ShakingActivity extends AppCompatActivity {

    Context myContext;

    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        myContext = this;

        setContentView(R.layout.activity_shaking);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                paymentDone();

            }
        }, 3000);
    }

    private void paymentDone () {

        Intent intent = new Intent(myContext, PaymentDoneActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


}
