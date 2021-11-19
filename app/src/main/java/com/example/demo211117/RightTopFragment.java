package com.example.demo211117;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RightTopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        doFragment2(view);
        return view;
    }


    private void doFragment2(View view){
        Button fragment2SaveBtn = (Button) view.findViewById(R.id.fragment2Btn1);
        Button fragment2ShowBtn = (Button) view.findViewById(R.id.fragment2Btn2);
        EditText fragment2EditText = (EditText) view.findViewById(R.id.fragment2EditText);
        TextView fragment2TextView = (TextView) view.findViewById(R.id.fragment2TextView);

        fragment2SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("DataOfFragment2", Context.MODE_PRIVATE).edit();
                editor.putString("data",fragment2EditText.getText().toString());
                editor.apply();
            }
        });

        fragment2ShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("DataOfFragment2", Context.MODE_PRIVATE);
                String str = preferences.getString("data", null);
                fragment2TextView.setText(str);
            }
        });
    }


}
