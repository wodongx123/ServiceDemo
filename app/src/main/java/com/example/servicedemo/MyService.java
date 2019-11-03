package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    int i=0;


    private CountBinder countBinder = new CountBinder();
    
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: " + toString());
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: " + toString());
        return countBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: " + toString());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    class CountBinder extends Binder{


        Thread countThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i<100; i++){
                    Log.i(TAG, "startCount: " + Thread.currentThread() + "   " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        public void startCount(){
            countThread.start();
        }

        public int getI(){
            return i;
        }
        
        public void func(){
            Log.i(TAG, "func: ");
        }
    }

}
