package com.firstexample.emarkova.session6;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyService4 extends Service {
    static final int MSG_REGISTER_CLIENT1 = 1;
    static final int MSG_SET_TXT_VAL = 3;
    private static final int TIME_OUT = 2;
   // private static final int RAD_DELTA = 10;
    Random mRandom = new Random();
    Messenger mClient = null;
    Messenger mMesseger = new Messenger(new IncomingHandler());


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (mClient != null) {
                        Message msg = Message.obtain(null, MSG_SET_TXT_VAL, mRandom.nextInt(10), 30);
                        try {
                            mClient.send(msg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(TIME_OUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mMesseger.getBinder();
    }


    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT1:
                    mClient = msg.replyTo;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    class MyBinder extends Binder {
        MyService4 getService() {
            return MyService4.this;
        }
    }
}
