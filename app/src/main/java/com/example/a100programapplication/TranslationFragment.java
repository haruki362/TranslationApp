package com.example.a100programapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class TranslationFragment extends Fragment {
    View rootview;
    public TranslationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview =  inflater.inflate(R.layout.fragment_translation, container, false);

        ImageButton clear = rootview.findViewById(R.id.clearButton);
        clear.setOnClickListener(new ButtonClickListener());

        return rootview;
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view){
            EditText input = rootview.findViewById(R.id.inputText);
            input.setText("");
        }
    }
}