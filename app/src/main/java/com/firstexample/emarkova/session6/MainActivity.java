package com.firstexample.emarkova.session6;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Fragment fragment3;
    Fragment fragment2;
    Button button3;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, MyIntentService1.class));
        startService(new Intent(MainActivity.this, MyIntentService3.class));
        startService(new Intent(MainActivity.this, MyService2.class));
        startService(new Intent(MainActivity.this, MyService4.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragment2 = getSupportFragmentManager().findFragmentById(R.id.fragmenttwo);
        fragment3 = getSupportFragmentManager().findFragmentById(R.id.fragmentthree);
        button3 = (Button) fragment3.getView().findViewById(R.id.button2Frag3);
        button2 = (Button)(fragment2.getView().findViewById(R.id.button));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
