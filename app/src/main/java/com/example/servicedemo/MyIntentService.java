package com.example.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    int i;

    public MyIntentService() {
        super("111");
        Log.i(TAG, "MyIntentService: ");
        //super()中的参数就是IntentService内部线程的名字


    }


    //主要逻辑都放在这里处理
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent: " + Thread.currentThread().getName());
        for (; i<100; ){
            i++;
            Log.i(TAG, "onHandleIntent: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
