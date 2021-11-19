package com.example.demo211117;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeftBottomFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        doFragment3(view);
        return view;
    }

    private void doFragment3(View view){
        Button fragment3Btn = (Button) view.findViewById(R.id.fragment3Btn);
        EditText fragment3EditText = (EditText) view.findViewById(R.id.fragment3EditText);



        fragment3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView fragment3WebView = (WebView) view.findViewById(R.id.fragment3WebView);
                fragment3WebView.getSettings().setJavaScriptEnabled(true);
                fragment3WebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url == null) {
                            return false;
                        }

                        try{
                            if(!url.startsWith("http://") && !url.startsWith("https://")){
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                                return true;
                            }
                        }catch (Exception e){//防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                            return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                        }

                        //下面的两种方式选择使用其中一种即可
                        // TODO Auto-generated method stub
                        //1：返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url);
                        return true;
                        //2：或者使用如下的加载方式：
                        //return super.shouldOverrideUrlLoading(view, url);
                    }
                });
                //fragment3WebView.setWebViewClient(new WebViewClient());
                fragment3WebView.loadUrl(fragment3EditText.getText().toString());

            }
        });
    }
}
