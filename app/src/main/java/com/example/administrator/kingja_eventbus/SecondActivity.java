package com.example.administrator.kingja_eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.eventcar.EventCar;
import com.kingja.eventcar.MessageEvent;


/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/22 10:13
 * 修改备注：
 */
public class SecondActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private MessageEvent mMessageEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mMessageEvent = new MessageEvent("来Second的消息");


    }

    public void goMainActivity(View view) {
        EventCar.getDefault().post(mMessageEvent);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
