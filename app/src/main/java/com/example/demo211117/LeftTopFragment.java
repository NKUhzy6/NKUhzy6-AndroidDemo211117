package com.example.demo211117;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;


public class LeftTopFragment extends Fragment {
    private TextView fragment1Text;
    public static final int UPDATE_TEXT = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        doFragment1(view);
        return view;
    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    Random ra = new Random();
                    int num = ra.nextInt(1000+1);
                    fragment1Text.setText(String.valueOf(num));
                    break;
                default:
                    break;
            }
        }
    };

    private void doFragment1(View view){
        Button fragment1Btn = (Button) view.findViewById(R.id.fragment1Btn);
        fragment1Text = (TextView) view.findViewById(R.id.fragment1Text);
        fragment1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){

                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            handler.sendMessage(message);
                            /*runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    fragment1EditText.setText(String.valueOf(num));
                                }
                            });*/
                            try {
                                Thread.sleep(3000);
                            }catch (Exception e){
                                Log.e("TAG", "run: ");
                            }
                        }
                    }
                }).start();
            }
        });

    }
}
