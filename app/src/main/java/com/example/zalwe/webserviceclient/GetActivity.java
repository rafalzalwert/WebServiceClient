package com.example.zalwe.webserviceclient;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetActivity extends AppCompatActivity {

    private static InputStream response;
    private EditText editTextPostId;
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        editTextPostId = findViewById(R.id.editTextPostId);
        Button buttonGet = findViewById(R.id.buttonDelete);
        textViewData = findViewById(R.id.textViewData);

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPostId.getText().toString().equals("")) {
                    Toast.makeText(GetActivity.this, "Enter post id", Toast.LENGTH_SHORT).show();
                } else {
                    fetchData();
                }
            }
        });

    }

    private void fetchData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MainActivity.apiEndpoint + "/" + editTextPostId.getText().toString());
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    if(httpsURLConnection.getResponseCode() == 200) {
                        response = httpsURLConnection.getInputStream();
                        populateTextView();
                        httpsURLConnection.disconnect();
                    }
                } catch (Exception exception) {
                    Log.e("Something went wrong: ", exception.getMessage());
                }
            }
        });
    }

    private void populateTextView() {
        runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(response);
                    JsonReader jsonReader = new JsonReader(inputStreamReader);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String key = jsonReader.nextName();
                        if (key.equals("title")) {
                            String value = jsonReader.nextString();
                            textViewData.setText("Title: " + value + "\n");
                        } else if (key.equals("body")) {
                            String value = jsonReader.nextString();
                            textViewData.append("Body: " + value);
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                } catch (Exception exception) {
                    Log.e("Something went wrong: ", exception.getMessage());
                }
            }
        });
    }
}