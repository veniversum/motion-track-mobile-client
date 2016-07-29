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
public class WalletActivity extends AppCompatActivity {

    private ImageButton sendButton;
    private ImageButton receiveButton;

    private TextView title_wallet;
    private TextView title_amount;

    Context myContext;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wallet);

        myContext = this;

        sendButton = (ImageButton) findViewById(R.id.button_send);
        sendButton.setOnClickListener(clickedSend);

        sendButton = (ImageButton) findViewById(R.id.button_receive);
        sendButton.setOnClickListener(clickedReceive);

        title_wallet = (TextView) findViewById(R.id.title_wallet);
        title_amount = (TextView) findViewById(R.id.title_amount);

        Typeface fontBold = Typeface.createFromAsset(getAssets(), "TheSansSemiBold-Plain.ttf");

        title_wallet.setTypeface(fontBold);
        title_amount.setTypeface(fontBold);

    }

    View.OnClickListener clickedSend = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 1st button
            Intent intent = new Intent(myContext, SendMoneyActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener clickedReceive = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 1st button

        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }

    private void toggle() {


    }

    private void hide() {

    }

    @SuppressLint("InlinedApi")
    private void show() {

    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {

    }
}
