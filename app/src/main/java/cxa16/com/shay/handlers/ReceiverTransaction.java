package cxa16.com.shay.handlers;

import android.os.Handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class ReceiverTransaction extends Transaction {
    public ReceiverTransaction(String id, float[] data, TransactionEventListener listener) {
        super(id, data, listener);
        url = "http://e94826c3.ngrok.io/receive/";
    }

    @Override
    public void execute() {
        try {
            StringEntity entity = new StringEntity(buildPayload().toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            new AsyncHttpClient().post(myContext, url, entity, "application/json", new JsonHttpResponseHandler() {
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
        JSONObject geo = new JSONObject();
        geo.put("lat", latitude);
        geo.put("lng", longtitude);
        root.put("geo", geo);
        return root;
    }
}
