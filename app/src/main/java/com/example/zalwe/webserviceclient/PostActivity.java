package com.example.zalwe.webserviceclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damian.webserviceclient.R;

import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PostActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextTitle;
    private EditText editTextBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button buttonPost = findViewById(R.id.buttonPost);
        editTextId = findViewById(R.id.editTextIdPut);
        editTextTitle = findViewById(R.id.editTextTitlePut);
        editTextBody = findViewById(R.id.editTextBodyPut);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextId.getText().toString().equals("")
                        || editTextTitle.getText().toString().equals("")
                        || editTextBody.getText().toString().equals("")) {
                    Toast.makeText(PostActivity.this, "Inputs can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    makeRequest();
                }
            }
        });
    }

    private void makeRequest() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(MainActivity.apiEndpoint);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("POST");
                    Map<String, String> data = new HashMap<>();
                    data.put("userId", editTextId.getText().toString());
                    data.put("title", editTextTitle.getText().toString());
                    data.put("body", editTextBody.getText().toString());
                    JSONObject postData = new JSONObject(data);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.getOutputStream().write(postData.toString().getBytes());
                    if(httpsURLConnection.getResponseCode() == 201) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PostActivity.this, "Post successfully created", Toast.LENGTH_SHORT).show();
                            }
                        });
                        httpsURLConnection.disconnect();
                    }
                } catch (Exception exception) {
                    Log.e("Something went wrong: ", exception.getMessage());
                }
            }
        });
    }
}