package com.example.administrator.kingja_eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kingja.eventcar.EventCar;
import com.kingja.eventcar.MessageEvent;
import com.kingja.eventcar.Subscribe;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventCar.getDefault().register(this);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void goSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }


    @Subscribe
    public void onMessageEvent(final MessageEvent event) {
        tv.setText(event.getMessage());
    }
}
