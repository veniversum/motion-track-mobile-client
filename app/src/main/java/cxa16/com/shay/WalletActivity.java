package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WalletActivity extends AppCompatActivity {

    private ImageButton sendButton;
    private ImageButton receiveButton;

    private TextView title_wallet;
    private TextView title_amount;

    private GlobalVars globalVariable;

    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalVariable = (GlobalVars) getApplicationContext();

        setContentView(R.layout.activity_wallet);

        myContext = this;

        sendButton = (ImageButton) findViewById(R.id.button_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalVariable.setSender(true);
                Intent intent = new Intent(myContext, SendMoneyActivity.class);
                startActivity(intent);
            }
        });

        receiveButton = (ImageButton) findViewById(R.id.button_receive);
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalVariable.setSender(false);
                Intent intent = new Intent(myContext, ShakeStartActivity.class);
                startActivity(intent);
            }
        });

        title_wallet = (TextView) findViewById(R.id.title_wallet);
        title_amount = (TextView) findViewById(R.id.title_amount);

        Typeface fontBold = Typeface.createFromAsset(getAssets(), "TheSansSemiBold-Plain.ttf");

        title_wallet.setTypeface(fontBold);
        title_amount.setTypeface(fontBold);




    }

    @Override
    protected void onResume() {
        super.onResume();

        float totalMoney = 550;

        if (globalVariable.getTotal() > 0){
            totalMoney = globalVariable.getTotal();
        } else {
            globalVariable.setTotal(totalMoney);
        }

        String totalMoneyFormat = "$"+formatDecimal(totalMoney).trim();
        title_amount.setText(totalMoneyFormat);
    }

    public String formatDecimal(float number) {
        return String.format("%10.2f", number);
    }

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


}
