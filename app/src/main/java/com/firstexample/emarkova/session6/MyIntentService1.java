package com.firstexample.emarkova.session6;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyIntentService1 extends IntentService {

    private static final int TIME_OUT = 2;
    public static final String ACT_FILT = "emarkova.CHANGE_COLOR";
    Random mRandom = new Random();

    public MyIntentService1() {
        super("MyIntentService1");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (true) {
            Intent broadcastIntent = new Intent(ACT_FILT);
            broadcastIntent.putExtra("color", getColor());
            sendBroadcast(broadcastIntent);
            try {
                TimeUnit.SECONDS.sleep(TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getColor()
    {
        return Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
    }
}
