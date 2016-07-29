package cxa16.com.shay;

import android.app.Application;

/**
 * Created by bl2eaD on 30-Jul-16.
 */
public class GlobalVars extends Application {
    private boolean isSender;
    private float amount;
    private String user;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean getSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }
}
