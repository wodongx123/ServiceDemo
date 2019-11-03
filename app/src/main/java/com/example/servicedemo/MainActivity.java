package com.example.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";


    Button btnStartService;
    Button btnStopService;
    Button btnBindService;
    Button btnUnbindService;
    Button btnGetI;
    Button btnStartIntentService;
    Button btnStopIntentService;

    MyService.CountBinder binder;

    private  ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
            binder = (MyService.CountBinder)service;
            binder.startCount();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btn_start);
        btnStopService = findViewById(R.id.btn_stop);
        btnBindService = findViewById(R.id.btn_bind);
        btnUnbindService = findViewById(R.id.btn_unbind);
        btnGetI = findViewById(R.id.btn_get_i);
        btnStartIntentService = findViewById(R.id.btn_start_intentservice);
        btnStopIntentService = findViewById(R.id.btn_stop_intentservice);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnGetI.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        btnStopIntentService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                Intent start = new Intent(this, MyService.class);
                startService(start);
                break;
            case R.id.btn_stop:
                Intent stop = new Intent(this, MyService.class);
                stopService(stop);
                break;
            case R.id.btn_bind:
                Intent bind = new Intent(this, MyService.class);
                bindService(bind, conn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(conn);
                break;
            case R.id.btn_get_i:
                if (binder != null)
                    binder.getI();
                break;
            case R.id.btn_start_intentservice:
                Intent startIntent = new Intent(this, MyIntentService.class);
                startService(startIntent);
                break;
            case R.id.btn_stop_intentservice:
                Intent stopIntent = new Intent(this, MyIntentService.class);
                stopService(stopIntent);
                break;
        }

    }
}

