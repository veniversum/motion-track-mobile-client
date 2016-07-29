package cxa16.com.shay;

import android.app.Application;

/**
 * Created by bl2eaD on 30-Jul-16.
 */
public class GlobalVars extends Application {
    private boolean isSender;


    public boolean getSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }
}
