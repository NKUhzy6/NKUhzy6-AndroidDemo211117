package com.example.demo211117;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fragment1Btn = (Button) findViewById(R.id.fragment1Btn);
        EditText fragment1EditText = (EditText) findViewById(R.id.fragment1EditText);
        fragment1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            Random ra = new Random();
                            int num = ra.nextInt(1000+1);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    fragment1EditText.setText(String.valueOf(num));
                                }
                            });
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

        Button fragment2SaveBtn = (Button) findViewById(R.id.fragment2Btn1);
        Button fragment2ShowBtn = (Button) findViewById(R.id.fragment2Btn2);
        EditText fragment2EditText = (EditText) findViewById(R.id.fragment2EditText);
        TextView fragment2TextView = (TextView) findViewById(R.id.fragment2TextView);

        fragment2SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("DataOfFragment2", MODE_PRIVATE).edit();
                editor.putString("data",fragment2EditText.getText().toString());
                editor.apply();
            }
        });

        fragment2ShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("DataOfFragment2", MODE_PRIVATE);
                String str = preferences.getString("data", null);
                fragment2TextView.setText(str);
            }
        });


        Button fragment3Btn = (Button) findViewById(R.id.fragment3Btn);
        EditText fragment3EditText = (EditText) findViewById(R.id.fragment3EditText);

        fragment3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView fragment3WebView = (WebView) findViewById(R.id.fragment3WebView);
                fragment3WebView.getSettings().setJavaScriptEnabled(true);
                fragment3WebView.setWebViewClient(new WebViewClient());
                fragment3WebView.loadUrl(fragment3EditText.getText().toString());

            }
        });

        Button fragment4Btn = (Button) findViewById(R.id.fragment4Btn);
        List<String> titlesList = new ArrayList<>();
        //String[] data = {"1", "2", "3", "4", "5", "6","7", "8", "9", "10","1", "2", "3", "4", "5", "6","7", "8", "9", "10"};
        fragment4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();
                            for (int kk = 0; kk<10; kk++){
                                String data = "https://www.wanandroid.com/article/list/"+kk+"/json";
                                Request request = new Builder().url(data).build();
                                Response response = client.newCall(request).execute();
                                String jsonData = response.body().string();

                                JSONObject jsonObject = new JSONObject(jsonData);
                                //jsonObject = jsonObject.getJSONObject("data");
                                String data0 = jsonObject.getString("data");

                                JSONObject jsonObject1 = new JSONObject(data0);
                                JSONArray jsonArray =new JSONArray();
                                jsonArray = jsonObject1.getJSONArray("datas");


                                for (int i =0; i<jsonArray.length(); i++){
                                    String title = jsonArray.getJSONObject(i).getString("title");
                                    titlesList.add(title);
                                }
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ListView fragment4ListView = (ListView) findViewById(R.id.fragment4ListView);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titlesList);
                                    fragment4ListView.setAdapter(adapter);
                                }
                            });


                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e("TAG", "run: 000");
                        }

                    }
                }).start();


            }
        });

    }



}