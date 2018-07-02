package com.firstexample.emarkova.session6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

public class Fragment2 extends Fragment {
    private static final int AMOUNT_BUT = 5;
    Button buttonCen;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Messenger mClient = new Messenger(new IncomingHandler());
    Messenger mService = null;
    ServiceConnection mConnection;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mService = new Messenger(iBinder);
                Message msg = Message.obtain(null, MyService2.MSG_REGISTER_CLIENT);
                msg.replyTo = mClient;
                try {
                    mService.send(msg);
                    Log.d("Logs", "SENT");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        buttonCen = (Button)view.findViewById(R.id.button);
        button1 = (Button)view.findViewById(R.id.button1Frag2);
        button2 = (Button)view.findViewById(R.id.button2Frag2);
        button3 = (Button)view.findViewById(R.id.button3Frag2);
        button4 = (Button)view.findViewById(R.id.button4Frag2);
        Intent intent = new Intent(getActivity().getBaseContext(), MyService2.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyService2.MSG_SET_COL_VAL:
                    switch (new Random().nextInt(AMOUNT_BUT) + 1) {
                        case 1:
                            button1.setBackgroundColor(msg.arg1);
                            break;
                        case 2:
                            button2.setBackgroundColor(msg.arg1);
                            break;
                        case 3:
                            button3.setBackgroundColor(msg.arg1);
                            break;
                        case 4:
                            button4.setBackgroundColor(msg.arg1);
                            break;
                        case 5:
                            buttonCen.setBackgroundColor(msg.arg1);
                            break;
                        default:
                    }
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
