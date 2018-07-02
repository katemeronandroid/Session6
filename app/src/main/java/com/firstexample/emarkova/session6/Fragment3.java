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
import android.widget.EditText;

import java.util.Random;

public class Fragment3 extends Fragment {
    private CustomBroadcastReciever mReciever;
    private IntentFilter mFilter;
    Button button1;
    Button button2;
    Button button3;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mReciever = new CustomBroadcastReciever();
        mFilter = new IntentFilter(MyIntentService3.ACT_FILT);
        button1 = (Button)view.findViewById(R.id.button1Frag3);
        button2 = (Button)view.findViewById(R.id.button2Frag3);
        button3 = (Button)view.findViewById(R.id.button3Frag3);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mReciever, mFilter);
    }


    private class CustomBroadcastReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (new Random().nextInt(3)+1) {
                case 1:
                    button1.setBackgroundColor((Integer) intent.getExtras().get("color"));
                    button1.setText(""+intent.getExtras().get("text"));
                break;
                case 2:
                    button2.setBackgroundColor((Integer) intent.getExtras().get("color"));
                    button2.setText(""+intent.getExtras().get("text"));
                    View viewToTwo = getActivity().findViewById(R.id.fragmenttwo);
                    Button buttonCen = (Button) viewToTwo.findViewById(R.id.button);
                    buttonCen.setText(button2.getText());
                    break;
                case 3:
                    button3.setBackgroundColor((Integer) intent.getExtras().get("color"));
                    button3.setText(""+intent.getExtras().get("text"));
                    break;
                default:
            }

        }
    }
}
