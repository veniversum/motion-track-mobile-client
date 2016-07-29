package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ShakeStartActivity extends AppCompatActivity {

    private ImageButton shakeButton;
    private TextView title_tap;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shake_start);

        myContext = this;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        shakeButton = (ImageButton) findViewById(R.id.button_shake);
        shakeButton.setOnClickListener(clickedShake);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available


        title_tap = (TextView) findViewById(R.id.title_tap);
        Typeface fontLight = Typeface.createFromAsset(getAssets(), "TheSansExtraLight-Plain.ttf");
        title_tap.setTypeface(fontLight);
    }

    View.OnClickListener clickedShake = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 1st button
            Intent intent = new Intent(myContext, ShakingActivity.class);
            startActivity(intent);
        }
    };


}
