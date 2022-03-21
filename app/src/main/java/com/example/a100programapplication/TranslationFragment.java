package com.example.a100programapplication;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
        ImageButton copy1 = rootview.findViewById(R.id.imageButton);
        copy1.setOnClickListener(new ButtonClickListener());

        return rootview;
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view){
            EditText input = rootview.findViewById(R.id.inputText);
            TextView output = rootview.findViewById(R.id.textView);

            int id = view.getId();
            switch (id){
                case R.id.clearButton:
                    input.setText("");
                    break;
                case R.id.imageButton:
                    String[] mimeType = new String[1];
                    mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;

                    ClipData.Item item = new ClipData.Item(output.getText());

                    ClipData CD = new ClipData(new ClipDescription("text", mimeType), item);

                    ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setPrimaryClip(CD);
                    break;
            }
        }
    }
}