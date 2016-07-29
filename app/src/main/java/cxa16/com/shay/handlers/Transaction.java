package cxa16.com.shay.handlers;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public abstract class Transaction{
    protected float[] data;
    protected float latitude;
    protected float longtitude;
    protected String id;
    protected String uid;
    protected float amount;
    protected Status status = Status.INACTIVE;
    AsyncHttpClient client;

    public enum Status {PENDING, SUCCESS, INVALID, INACTIVE}

    protected static String url;
    protected TransactionEventListener listener;

    protected Context myContext;
    protected Transaction myTransaction;

    public void setListener(TransactionEventListener listener) {
        this.listener = listener;
    }

    public Transaction(String id, float[] data, TransactionEventListener listener) {
        this.id = id;
        this.data = data;
        this.listener = listener;
        myTransaction = this;
        client = new AsyncHttpClient();
        client.setConnectTimeout(10000);
        client.setMaxConnections(10);
    }

    public void setLatLng(float lat, float lng) {
        latitude = lat;
        longtitude = lng;
    }

    public abstract void execute();



    public interface TransactionEventListener {
        void onSuccess(Transaction t, float amount, String other);
        void onError(Transaction t);
    }

    protected void checkStatus() {
        Log.i("url", url + uid);
        new AsyncHttpClient().get(myContext, url + uid, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    switch (response.getString("status")) {
                        case "success":
                            status = Status.SUCCESS;
                            listener.onSuccess(myTransaction, (float) response.getJSONObject("data").getDouble("amount"), response.getJSONObject("data").getString("other_id"));
                            break;
                        case "pending":
                            status = Status.PENDING;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    checkStatus();
                                }
                            }, 500);
                            break;
                        case "invalid_or_expired":
                            listener.onError(myTransaction);
                            status = Status.INVALID;
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                status = Status.INVALID;
                listener.onError(myTransaction);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                status = Status.INVALID;
                listener.onError(myTransaction);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                status = Status.INVALID;
                listener.onError(myTransaction);
            }
        });
    }
}
