package com.firstexample.emarkova.session6;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyIntentService3 extends IntentService {
    private static final int TIME_OUT = 2;
    public static final String ACT_FILT = "emarkova.CHANGE_COLOR_3";
    Random mRandom = new Random();

    public MyIntentService3() {

        super("MyIntentService3");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            Intent broadcastIntent = new Intent(ACT_FILT);
            broadcastIntent.putExtra("color", getColor());
            broadcastIntent.putExtra("text", mRandom.nextInt(10));
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
