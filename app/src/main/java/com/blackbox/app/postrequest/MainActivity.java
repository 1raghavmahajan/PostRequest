package com.blackbox.app.postrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText eT_Key1, eT_Key2, eT_Key3, eT_Key4;
    EditText eT_Value1, eT_Value2, eT_Value3, eT_Value4;
    EditText eT_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eT_Key1 = (EditText) findViewById(R.id.eT_key1);
        eT_Key2 = (EditText) findViewById(R.id.eT_key2);
        eT_Key3 = (EditText) findViewById(R.id.eT_key3);
        eT_Key4 = (EditText) findViewById(R.id.eT_key4);

        eT_Value1 = (EditText) findViewById(R.id.eT_Value1);
        eT_Value2 = (EditText) findViewById(R.id.eT_Value2);
        eT_Value3 = (EditText) findViewById(R.id.eT_Value3);
        eT_Value4 = (EditText) findViewById(R.id.eT_Value4);

        eT_url = (EditText) findViewById(R.id.eT_url);
        eT_url.setText("http://httpbin.org/post");

    }

    public void sendPost(View view) {

        Map<String, String> params = new HashMap<>();
        if (!eT_Key1.getText().toString().trim().equals("") && !eT_Value1.getText().toString().trim().equals(""))
            params.put(eT_Key1.getText().toString().trim(), eT_Value1.getText().toString().trim());
        if (!eT_Key2.getText().toString().trim().equals("") && !eT_Value2.getText().toString().trim().equals(""))
            params.put(eT_Key2.getText().toString().trim(), eT_Value2.getText().toString().trim());
        if (!eT_Key3.getText().toString().trim().equals("") && !eT_Value3.getText().toString().trim().equals(""))
            params.put(eT_Key3.getText().toString().trim(), eT_Value3.getText().toString().trim());
        if (!eT_Key4.getText().toString().trim().equals("") && !eT_Value4.getText().toString().trim().equals(""))
            params.put(eT_Key4.getText().toString().trim(), eT_Value4.getText().toString().trim());

        if (params.size() != 0) {
            if (!eT_url.getText().toString().trim().equals("")) {
                PostRequest post = new PostRequest(params, eT_url.getText().toString().trim());
                post.send(getApplicationContext());
            }
        }
    }

    @Override
    protected void onDestroy() {
        //unregisterReceiver(receiver);
        super.onDestroy();
    }

}
