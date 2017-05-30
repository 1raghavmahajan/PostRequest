package com.blackbox.app.postrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        response = (TextView) findViewById(R.id.response);

        response.setText(getIntent().getStringExtra("result"));

    }
}
