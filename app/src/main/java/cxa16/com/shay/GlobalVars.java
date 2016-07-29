package cxa16.com.shay;

import android.app.Application;

/**
 * Created by bl2eaD on 30-Jul-16.
 */
public class GlobalVars extends Application {
    private boolean isSender;
    private float amount;
    private float total;
    private String user;
    private String other;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getOther() {return other;}

    public void setOther(String other) {
        this.other = other;
    }

    public boolean getSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }
}
