package com.example.demo211117;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RightBottomFragment extends Fragment {

    private ListView fragment4ListView;
    private List<String> titlesList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        fragment4ListView = (ListView) view.findViewById(R.id.fragment4ListView);
        doFragment4(view);
        return view;
    }

    private static final int UPDATE = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE:
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titlesList);
                    fragment4ListView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };

    private void doFragment4(View view){
        Button fragment4Btn = (Button) view.findViewById(R.id.fragment4Btn);
        titlesList = new ArrayList<>();
        //String[] data = {"1", "2", "3", "4", "5", "6","7", "8", "9", "10","1", "2", "3", "4", "5", "6","7", "8", "9", "10"};
        fragment4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();
                            for (int page = 0; page<10; page++){
                                String data = "https://www.wanandroid.com/article/list/"+ page+ "/json";
                                Request request = new Request.Builder().url(data).build();
                                Response response = client.newCall(request).execute();
                                String jsonData = response.body().string();
                                //Gson
                                JSONObject jsonObject = new JSONObject(jsonData);
                                //jsonObject = jsonObject.getJSONObject("data");
                                String data0 = jsonObject.optString("data");

                                JSONObject jsonObject1 = new JSONObject(data0);
                                JSONArray jsonArray =new JSONArray();
                                jsonArray = jsonObject1.optJSONArray("datas");


                                for (int i =0; i<jsonArray.length(); i++){
                                    String title = jsonArray.getJSONObject(i).optString("title");
                                    titlesList.add(title);
                                }
                            }

                            /*getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ListView fragment4ListView = (ListView) view.findViewById(R.id.fragment4ListView);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titlesList);
                                    fragment4ListView.setAdapter(adapter);
                                }
                            });*/
                            Message message = new Message();
                            message.what = UPDATE;
                            handler.sendMessage(message);

                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e("TAG", "run: 000");
                        }

                    }
                };
                ThreadPoolSingleton.getInstance().threadPoolExecutor.execute(runnable);


            }
        });
    }
}
