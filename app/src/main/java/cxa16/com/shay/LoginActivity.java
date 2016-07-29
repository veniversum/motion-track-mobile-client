package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Logger;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView titleApp;
    private TextView titleTag;
    private EditText fieldUID;
    private EditText fieldPWD;

    Context myContext;

    private int uidFieldLength;
    private int pwdFieldLength;

    String loginCat;

    private SharedPreferences prefs;
    private GlobalVars globalVariable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        globalVariable = (GlobalVars) getApplicationContext();


        setContentView(R.layout.activity_login);

        myContext = this;

        titleApp = (TextView) findViewById(R.id.title_app);
        titleTag = (TextView) findViewById(R.id.title_tag);
        fieldUID = (EditText) findViewById(R.id.uid_field);
        fieldPWD = (EditText) findViewById(R.id.pwd_field);

        Typeface fontLogo = Typeface.createFromAsset(getAssets(), "FREESCPT.TTF");
        Typeface fontLight = Typeface.createFromAsset(getAssets(), "TheSansExtraLight-Plain.ttf");
        Typeface fontBold = Typeface.createFromAsset(getAssets(), "TheSansSemiBold-Plain.ttf");

        titleApp.setTypeface(fontLogo);
        titleTag.setTypeface(fontLight);

        loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(clickedLogin);

        validateLogin();

        fieldUID.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                uidFieldLength = s.length();
                validateLogin();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        fieldPWD.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                pwdFieldLength = s.length();
                validateLogin();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}

        });

        prefs = this.getSharedPreferences(
                "com.example.app", Context.MODE_PRIVATE);

    }




    private void validateLogin () {

        if (uidFieldLength > 2 && pwdFieldLength > 2){
            loginButton.setEnabled(true);
            loginButton.setBackgroundColor(Color.argb(255, 63, 100, 168 ));
        } else {
            loginButton.setEnabled(false);
            loginButton.setBackgroundColor(Color.GRAY);
        }

    }

    private void submitLogin () {

        String uidInput = fieldUID.getText().toString();
        Boolean valid = false;

        if (uidInput.equals("renjie")){
            valid = true;
            loginCat = "Normal";
        } else if (uidInput.equals("qingzhuo")){
            valid = true;
            loginCat = "Stationary";
        } else if (uidInput.equals("malie")){
            valid = true;
            loginCat = "Food";
        }

        if (valid){
            globalVariable.setUser(fieldUID.getText().toString());
            fieldUID.setText("");
            fieldPWD.setText("");

            prefs.edit().putString("category", loginCat).apply();
            prefs.edit().putString("loggedInId", uidInput).apply();

            Intent intent = new Intent(myContext, WalletActivity.class);
            startActivity(intent);

        } else {
            erroLogin();
        }
    }

    private void erroLogin () {

        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        alertDialog.setTitle("Login Error");
        alertDialog.setMessage("Invalid Login Id");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    View.OnClickListener clickedLogin = new View.OnClickListener() {
        public void onClick(View v) {
            submitLogin();
        }
    };


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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }


    private void hide() {
    }


}
