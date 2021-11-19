package com.example.demo211117;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView fragmentText1;
    private TextView fragmentText2;
    private TextView fragmentText3;
    private TextView fragmentText4;
    private FrameLayout fragmentContent;

    private LeftTopFragment fragment1;
    private RightTopFragment fragment2;
    private LeftBottomFragment fragment3;
    private RightBottomFragment fragment4;

    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        bindViews();
        fragmentText1.performClick();
    }

    private void bindViews(){
        fragmentText1 = (TextView) findViewById(R.id.fragment_1);
        fragmentText2 = (TextView) findViewById(R.id.fragment_2);
        fragmentText3 = (TextView) findViewById(R.id.fragment_3);
        fragmentText4 = (TextView) findViewById(R.id.fragment_4);
        fragmentContent = (FrameLayout) findViewById(R.id.content);

        fragmentText1.setOnClickListener((View.OnClickListener) this);
        fragmentText2.setOnClickListener((View.OnClickListener) this);
        fragmentText3.setOnClickListener((View.OnClickListener) this);
        fragmentText4.setOnClickListener((View.OnClickListener) this);



    }

    private void setSelected(){
        fragmentText1.setSelected(false);
        fragmentText1.setBackgroundColor(Color.TRANSPARENT);
        fragmentText2.setSelected(false);
        fragmentText2.setBackgroundColor(Color.TRANSPARENT);
        fragmentText3.setSelected(false);
        fragmentText3.setBackgroundColor(Color.TRANSPARENT);
        fragmentText4.setSelected(false);
        fragmentText4.setBackgroundColor(Color.TRANSPARENT);
    }










    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if(fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }
        if(fragment3 != null) {
            fragmentTransaction.hide(fragment3);
        }
        if(fragment4 != null) {
            fragmentTransaction.hide(fragment4);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.fragment_1:
                setSelected();
                fragmentText1.setSelected(true);
                fragmentText1.setBackgroundColor(Color.parseColor("#ffffe0"));
                if (fragment1 == null){
                    fragment1 = new LeftTopFragment();
                    fTransaction.add(R.id.content, fragment1);
                }else{
                    fTransaction.show(fragment1);
                }
                break;
            case R.id.fragment_2:
                setSelected();
                fragmentText2.setSelected(true);
                fragmentText2.setBackgroundColor(Color.parseColor("#ffffe0"));
                if (fragment2 == null){
                    fragment2 = new RightTopFragment();
                    fTransaction.add(R.id.content, fragment2);
                }else{
                    fTransaction.show(fragment2);
                }
                break;
            case R.id.fragment_3:
                setSelected();
                fragmentText3.setSelected(true);
                fragmentText3.setBackgroundColor(Color.parseColor("#ffffe0"));
                if (fragment3 == null){
                    fragment3 = new LeftBottomFragment();
                    fTransaction.add(R.id.content, fragment3);
                }else{
                    fTransaction.show(fragment3);
                }
                break;
            case R.id.fragment_4:
                setSelected();
                fragmentText4.setSelected(true);
                if (fragment4 == null){
                    fragment4 = new RightBottomFragment();
                    fragmentText4.setBackgroundColor(Color.parseColor("#ffffe0"));
                    fTransaction.add(R.id.content, fragment4);
                }else{
                    fTransaction.show(fragment4);
                }
                break;

        }
        fTransaction.commit();
    }
}