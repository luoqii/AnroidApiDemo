package com.bysong.apidemo.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.bysong.apidemo.aidlserver.IRemoteService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    static int ACCOUNT = 0;
    ServiceConnection mCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindServer(View view) {
        Intent service = new Intent();
        ComponentName component = new ComponentName("com.bysong.apidemo.aidlserver", "com.bysong.apidemo.aidlserver.AidlService");
        service.setComponent(component);
        ServiceConnection cb = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "#" + (ACCOUNT++) + " onServiceConnected name:" + name + " service:" + service);
                IRemoteService s = IRemoteService.Stub.asInterface(service);
                int pid = 0;
                try {
                    pid = s.getPid();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "pid:" + pid);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.w(TAG, "#" + (ACCOUNT++) + " onServiceDisconnected name:" + name);
            }
        };
        mCb = cb;
        boolean result = bindService(service, mCb, BIND_AUTO_CREATE);
        Log.d(TAG, "bind result:" + result);
    }

    public void unbindServer(View view) {
        unbindService(mCb);
    }
}