package com.bysong.apidemo.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AidlService extends Service {
    private static final String TAG = AidlService.class.getSimpleName();
    private final IRemoteService.Stub binder = new IRemoteService.Stub() {
        public int getPid(){
            return 1234;
        }

        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }
    };

    public AidlService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand intent:" + intent + " flags:" + flags + " startId:" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "onBind intent:" + intent);
        return binder;
    }
}