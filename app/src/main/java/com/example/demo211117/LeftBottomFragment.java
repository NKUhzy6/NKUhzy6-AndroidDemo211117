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

                fragment3WebView.setWebViewClient(new WebViewClient());
                fragment3WebView.loadUrl(fragment3EditText.getText().toString());

            }
        });
    }
}
