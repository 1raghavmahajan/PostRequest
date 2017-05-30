package com.blackbox.app.postrequest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


class PostRequest {

    private static final String TAG = PostRequest.class.getSimpleName() + " YOYO";

    private String url;
    private Map<String, String> params;

    PostRequest(Map<String, String> params, String url) {

        this.url = url;
        this.params = params;
    }

    void send(final Context context) {

        // Tag used to cancel the request
        String request_Tag = "POST_REQUEST";


        StringRequest strReq = new StringRequest
                (
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Intent i = new Intent();
                                i.setAction("com.BlackBox.app.ACTION_RESULT");
                                i.putExtra("result", response);
                                i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(i);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //VolleyLog.d(TAG, "onErrorResponse: " + error.getMessage());
                                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public void deliverError(final VolleyError error) {

                if (error != null)
                    Log.i(TAG, "Error details: " + error.toString());

                Toast.makeText(context, "Unknown deliveryError", Toast.LENGTH_SHORT).show();

            }

        };

        // Adding request to request queue
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        strReq.setTag(request_Tag);
        requestQueue.add(strReq);
    }

}
