package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PaymentDoneActivity extends AppCompatActivity {

    private ImageView icon_success;
    private TextView title_successfully_paid;
    private TextView title_dollar;
    private TextView title_to_from;
    private RelativeLayout content_status;
    private Button backButton;


    private SharedPreferences prefs;

    private GlobalVars globalVariable;
    private boolean isSender;
    private String other;

    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalVariable = (GlobalVars) getApplicationContext();
        isSender = globalVariable.getSender();
        setContentView(R.layout.activity_payment_done);

        myContext = this;

        icon_success = (ImageView) findViewById(R.id.icon_success);
        title_successfully_paid = (TextView) findViewById(R.id.title_successfully_paid);
        title_dollar = (TextView) findViewById(R.id.title_dollar);
        title_to_from = (TextView) findViewById(R.id.title_to_from);
        content_status = (RelativeLayout) findViewById(R.id.content_status);
        backButton = (Button) findViewById(R.id.button_back);

        Typeface fontLight = Typeface.createFromAsset(getAssets(), "TheSansExtraLight-Plain.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(), "TheSansSemiBold-Plain.ttf");

        title_successfully_paid.setTypeface(fontLight);
        title_dollar.setTypeface(fontBold);
        title_to_from.setTypeface(fontLight);

        icon_success.setVisibility(View.GONE);

        prefs = this.getSharedPreferences(
                "com.example.app", Context.MODE_PRIVATE);

        //float totalMoney = prefs.getFloat("totalMoney", 0);
        //float sentAmount = prefs.getFloat("sendAmount", 0);

        float totalMoney = globalVariable.getTotal();
        float sentAmount = globalVariable.getAmount();






        String totalMoneyFormat = "$"+formatDecimal(sentAmount).trim();
        title_dollar.setText(totalMoneyFormat);


        backButton = (Button) findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(myContext, WalletActivity.class);
                startActivity(intent);

            }
        });

        other = globalVariable.getOther();

        if (isSender){
            totalMoney -= sentAmount;
            title_successfully_paid.setText("Successfully paid");
            title_to_from.setText("to "+other);
        } else {
            totalMoney += sentAmount;
            title_successfully_paid.setText("Successfully received");
            title_to_from.setText("from "+other);
        }


        globalVariable.setTotal(totalMoney);
        prefs.edit().putFloat("totalMoney", totalMoney).apply();

    }


    public String formatDecimal(float number) {
        return String.format("%10.2f", number);
    }

    @Override
    protected void onResume() {
        super.onResume();
        icon_success.setVisibility(View.VISIBLE);
        scaleView(icon_success, 0f, 1f);
        alphaView(content_status, 0, 1);
    }

    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(1000);
        anim.setRepeatCount(0);
        v.startAnimation(anim);
    }


    public void alphaView(View v, float startAlpha, float endAlpha) {
        Animation fadeIn = new AlphaAnimation(startAlpha, endAlpha);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1500);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        v.startAnimation(animation);
    }


}
