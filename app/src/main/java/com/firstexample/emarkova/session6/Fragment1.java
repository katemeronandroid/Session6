package com.firstexample.emarkova.session6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

public class Fragment1 extends Fragment {
    private static final int AMOUNT_BUT = 3;
    Button button1;
    Button button2;
    Button button3;
    CustomBroadcastReciever mReciever;
    IntentFilter mFilter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mReciever = new CustomBroadcastReciever();
        mFilter = new IntentFilter(MyIntentService1.ACT_FILT);
        button1 = view.findViewById(R.id.button1Frag1);
        button2 = view.findViewById(R.id.button2Frag1);
        button3 = view.findViewById(R.id.button3Frag1);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mReciever,mFilter);
    }

    private class CustomBroadcastReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (new Random().nextInt(AMOUNT_BUT) + 1) {
                case 1:
                    button1.setBackgroundColor((Integer) intent.getExtras().get("color"));
                    break;
                case 2:
                    button2.setBackgroundColor((Integer) intent.getExtras().get("color"));
                    break;
                case 3:
                    button3.setBackgroundColor((Integer) intent.getExtras().get("color"));
                    break;
                default:
            }
        }
    }
}
