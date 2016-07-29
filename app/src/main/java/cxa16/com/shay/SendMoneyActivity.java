package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SendMoneyActivity extends AppCompatActivity {

    private ImageButton sendButton;
    private TextView title_sendMoney;
    private EditText amount_field;
    Context myContext;
    private SharedPreferences prefs;

    int amountFieldLength = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send_money);

        myContext = this;

        title_sendMoney = (TextView) findViewById(R.id.title_sendMoney);
        sendButton = (ImageButton) findViewById(R.id.button_send);
        amount_field = (EditText) findViewById(R.id.amount_field);
        sendButton.setOnClickListener(clickedSend);

        Typeface fontBold = Typeface.createFromAsset(getAssets(), "TheSansSemiBold-Plain.ttf");

        title_sendMoney.setTypeface(fontBold);

        prefs = this.getSharedPreferences(
                "com.example.app", Context.MODE_PRIVATE);


        amount_field.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                amountFieldLength = s.length();
                validateSend();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}

        });
    }

    View.OnClickListener clickedSend = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 1st button
            Intent intent = new Intent(myContext, ShakeStartActivity.class);
            startActivity(intent);
        }
    };


    private void validateSend () {

        if (amountFieldLength > 0){
            sendButton.setEnabled(true);
        } else {
            sendButton.setEnabled(false);
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        amount_field.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(amount_field, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                    INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return true;
    }


}
