package com.example.a100programapplication;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class TranslationFragment extends Fragment {
    View rootview;
    private String urlPostText = "https://api.openai.com/v1/engines/text-davinci-002/completions";
    private String nameAndType = "";
    private String nameAndType1 = "";
    private String nameAndType2 = "";
    private Handler handler = new Handler();

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
        ImageButton copy1 = rootview.findViewById(R.id.imageButton1_1);
        copy1.setOnClickListener(new ButtonClickListener());
        ImageButton copy2 = rootview.findViewById(R.id.imageButton1_2);
        copy2.setOnClickListener(new ButtonClickListener());
        ImageButton copy3 = rootview.findViewById(R.id.imageButton1_3);
        copy3.setOnClickListener(new ButtonClickListener());
        Button trans = rootview.findViewById(R.id.button);
        trans.setOnClickListener(new TransListener());


        return rootview;
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view){
            EditText input = rootview.findViewById(R.id.inputText);
            TextView output = rootview.findViewById(R.id.textView);
            TextView output1 = rootview.findViewById(R.id.textView2);
            TextView output2 = rootview.findViewById(R.id.textView3);


            int id = view.getId();
            if(id == R.id.clearButton) {
                input.setText("");
            }else if(id ==  R.id.imageButton1_1){
                String[] mimeType = new String[1];
                mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;
                ClipData.Item item = new ClipData.Item(output.getText());
                ClipData CD = new ClipData(new ClipDescription("text", mimeType), item);
                ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(CD);
            }else if(id == R.id.imageButton1_2){
                String[] mimeType = new String[1];
                mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;
                ClipData.Item item = new ClipData.Item(output1.getText());
                ClipData CD = new ClipData(new ClipDescription("text", mimeType), item);
                ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(CD);
            }else if(id == R.id.imageButton1_3){
                String[] mimeType = new String[1];
                mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;
                ClipData.Item item = new ClipData.Item(output2.getText());
                ClipData CD = new ClipData(new ClipDescription("text", mimeType), item);
                ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(CD);
            }
        }
    }

    private class TransListener implements View.OnClickListener {
        TextView temp = rootview.findViewById(R.id.textView);
        TextView temp1 = rootview.findViewById(R.id.textView2);
        TextView temp2 = rootview.findViewById(R.id.textView3);

        EditText input = rootview.findViewById(R.id.inputText);
        String k = input.getText().toString();

        @Override
        public void onClick(View v) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String response = "";
                    String response1 = "";
                    String response2 = "";
                    try {
                        response = postAPI();
                        JSONObject rootJSON = new JSONObject(response);
                        JSONArray formJSON = rootJSON.getJSONArray("choices");
                        JSONObject s = formJSON.getJSONObject(0);
                        nameAndType = s.getString("text");
                        response1 = postAPI1();
                        JSONObject rootJSON1 = new JSONObject(response1);
                        JSONArray formJSON1 = rootJSON1.getJSONArray("choices");
                        JSONObject s1 = formJSON1.getJSONObject(0);
                        nameAndType1 = s1.getString("text");
                        response2 = postAPI2();
                        JSONObject rootJSON2 = new JSONObject(response2);
                        JSONArray formJSON2 = rootJSON2.getJSONArray("choices");
                        JSONObject s2 = formJSON2.getJSONObject(0);
                        nameAndType2 = s2.getString("text");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            temp.setText(nameAndType);
                            temp1.setText(nameAndType1);
                            temp2.setText(nameAndType2);
                        }
                    });

                }
            });
            thread.start();
        }
    }

    public String postAPI() {
        EditText input = rootview.findViewById(R.id.inputText);
        String k = input.getText().toString();
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        String str = "";
        URL url = null;
        try {
            url = new URL(urlPostText);
            urlConnection = (HttpURLConnection) url.openConnection();
            String postData = "{" +
                    "\"prompt\":\"????????????????????????????????????" +
                    "???:????????????????????????" +
                    "AI:??????????????????????????????" +
                    "???:????????????????????????" +
                    "AI:??????????????????????????? "+
                    "???:???????????????????????????????????????" +
                    "AI:??????????????????????????????????????????"+
                    "???:?????????????????????????????????????????????" +
                    "AI:????????????????????????????????????????????????"+
                    "???:??????????????????????????????" +
                    "AI:?????????????????????????????????"+
                    String.format("???:%s", k)+
                    "AI:\"," +
                    "\"max_tokens\":50,"+
                    "\"stop\": \".\""+
                    "}";
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.addRequestProperty("User-Agent", "Android");
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Authorization", "Bearer ");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                result = bufferedReader.readLine();
                while (result != null) {
                    str += result;
                    result = bufferedReader.readLine();
                }
                bufferedReader.close();
            }

            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String postAPI1() {
        EditText input = rootview.findViewById(R.id.inputText);
        String k = input.getText().toString();
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        String str = "";
        URL url = null;
        try {
            url = new URL(urlPostText);
            urlConnection = (HttpURLConnection) url.openConnection();
            String postData = "{" +
                    "\"prompt\":\"????????????????????????????????????" +
                    "???:???????????????????????????" +
                    "AI:?????????????????????????????????????????????" +
                    "???:????????????????????????" +
                    "AI:??????????????????????????????????????? "+
                    "???:??????????????????????????????????????????" +
                    "AI:?????????????????????????????????????????????????????????"+
                    "???:???????????????????????????????????????????????????" +
                    "AI:?????????????????????????????????????????????????????????????????????"+
                    "???:?????????????????????????????????" +
                    "AI:????????????????????????????????????????????????"+
                    String.format("???:%s", k)+
                    "AI:\"," +
                    "\"max_tokens\":50,"+
                    "\"stop\": \".\""+
                    "}";
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.addRequestProperty("User-Agent", "Android");
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Authorization", "Bearer ");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                result = bufferedReader.readLine();
                while (result != null) {
                    str += result;
                    result = bufferedReader.readLine();
                }
                bufferedReader.close();
            }

            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    public String postAPI2() {
        EditText input = rootview.findViewById(R.id.inputText);
        String k = input.getText().toString();
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String result = "";
        String str = "";
        URL url = null;
        try {
            url = new URL(urlPostText);
            urlConnection = (HttpURLConnection) url.openConnection();
            String postData = "{" +
                    "\"prompt\":\"????????????????????????????????????" +
                    "???:?????????????????????????????????" +
                    "AI:???????????????????????????????????????" +
                    "???:????????????????????????" +
                    "AI:????????????????????????????????? "+
                    "???:??????????????????????????????????????????" +
                    "AI:??????????????????????????????????????????"+
                    "???:?????????????????????????????????" +
                    "AI:??????????????????????????????????????????"+
                    "???:??????????????????????????????" +
                    "AI:???????????????????????????????????????"+
                    "???:???????????????????????????????????????????????????" +
                    "AI:????????????????????????????????????????????????????????????"+
                    String.format("???:%s", k)+
                    "AI:\"," +
                    "\"max_tokens\":50,"+
                    "\"stop\": \".\""+
                    "}";
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.addRequestProperty("User-Agent", "Android");
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Authorization", "Bearer ");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                result = bufferedReader.readLine();
                while (result != null) {
                    str += result;
                    result = bufferedReader.readLine();
                }
                bufferedReader.close();
            }

            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}