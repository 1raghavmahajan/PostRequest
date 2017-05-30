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


public class PostRequest {

    private static final String TAG = PostRequest.class.getSimpleName() + " YOYO";

    public static final String ACTION_RESULT = "com.BlackBox.app.ACTION_RESULT";

    String url;
    Map<String, String> params;

    public PostRequest(Map<String, String> params, String url) {

        this.url = url;
        this.params = params;
    }

    public void send(final Context context) {

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
                                //Intent i = new Intent(ACTION_RESULT);
                                //i.putExtra("resultStatus", false);
                                //context.sendBroadcast(i);
                            }
                        }
                ) {
            @Override
            protected Map<String, String> getParams() {

                return params;
            }

            @Override
            public void deliverError(final VolleyError error) {

                //Intent i = new Intent(ACTION_RESULT);

                String mess_str = "Unknown deliveryError";
                /*if (error != null) {
                    //Log.i(TAG, "Error details: " + error.toString());
                    if (error.toString().contains("Timeout")) {
                        mess_str = "Authentication server not reachable. Please try after some time.";
                    } else if (error.toString().contains("NoConnectionError")) {
                        mess_str = "No Connection. Please try after some time.";
                    } else {
                        if (error.networkResponse != null) //network response
                        {
                            final int status = error.networkResponse.statusCode;
                            //Log.i(TAG, "Status : " + status);
                            // Handle 30x
                            if (HttpURLConnection.HTTP_MOVED_PERM == status || status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_SEE_OTHER) {
                                final String location = error.networkResponse.headers.get("Location");

                                if (location.contains("bing")) {
                                    mess_str = "Successfully Authenticated!";
                                    i.putExtra("resultStatus", true);
                                } else {
                                    mess_str = "Invalid Credentials Provided.";
                                }
                            }
                        } else {
                            mess_str = "No Network response";
                        }
                    }
                }*/
                //Log.i("YOYO", "Message for noobs: " + mess_str);
                if (error != null)
                    Log.i(TAG, "Error details: " + error.toString());

                Toast.makeText(context, mess_str, Toast.LENGTH_SHORT).show();
                //context.sendBroadcast(i);
            }

        };

        // Adding request to request queue
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        strReq.setTag(request_Tag);
        requestQueue.add(strReq);
    }

}
