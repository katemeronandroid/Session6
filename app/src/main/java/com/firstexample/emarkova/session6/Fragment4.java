package com.firstexample.emarkova.session6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Fragment4 extends Fragment {
    TextView text1;
    TextView text2;
    Button button;
    Messenger mClient = new Messenger(new IncomingHandler());
    Messenger mService;
    ServiceConnection mConnection;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text1 = (TextView)view.findViewById(R.id.textView);
        text2 = (TextView)view.findViewById(R.id.textView2);
        button = (Button)view.findViewById(R.id.buttoncir);
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                mService = new Messenger(iBinder);
                Message msg = Message.obtain(null, MyService4.MSG_REGISTER_CLIENT1);
                msg.replyTo = mClient;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent4 = new Intent(getActivity().getBaseContext(), MyService4.class);
        getActivity().bindService(intent4, mConnection, Context.BIND_AUTO_CREATE);
    }

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyService4.MSG_SET_TXT_VAL:
                    switch (new Random().nextInt(2) + 1) {
                        case 1:
                            text1.setText(""+msg.arg1);
                            break;
                        case 2:
                            text2.setText(""+msg.arg1);
                            break;
                        default:
                    }
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)button.getLayoutParams();
                    float newAng = layoutParams.circleAngle + msg.arg2;
                    Log.d("Logs", ""+newAng);
                    Log.d("Logs", ""+layoutParams.circleRadius);
                    layoutParams.circleAngle = newAng;
                    button.setLayoutParams(layoutParams);
                default:
                    super.handleMessage(msg);
            }
        }
    }

}
