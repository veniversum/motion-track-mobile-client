package cxa16.com.shay.handlers;

import android.os.Handler;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class SenderTransaction extends Transaction {
    public SenderTransaction(String id, float amount, float[] data, TransactionEventListener listener) {
        super(id, data, listener);
        this.amount = amount;
        url = "http://e94826c3.ngrok.io/send/";
    }

    @Override
    public void execute() {
        try {
            StringEntity entity = new StringEntity(buildPayload().toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.post(myContext, url, entity, "application/json", new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        uid = response.getString("uid");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                checkStatus();
                            }
                        }, 500);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    listener.onError(myTransaction);
                }
            });
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject buildPayload() throws JSONException {
        JSONObject root = new JSONObject();
        root.put("id", id);
        root.put("data", new JSONArray(data));
        root.put("amount", amount);
        JSONObject geo = new JSONObject();
        geo.put("lat", latitude);
        geo.put("lng", longtitude);
        root.put("geo", geo );
        return root;
    }
}
