package com.example.zalwe.webserviceclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.damian.webserviceclient.R;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DeleteActivity extends AppCompatActivity {

    private EditText editTextPostId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        editTextPostId = findViewById(R.id.editTextPostId);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPostId.getText().toString().equals("")) {
                    Toast.makeText(DeleteActivity.this, "Enter post id", Toast.LENGTH_SHORT).show();
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
                    URL url = new URL(MainActivity.apiEndpoint + "/" + editTextPostId.getText().toString());
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("DELETE");
                    if(httpsURLConnection.getResponseCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DeleteActivity.this, "Post successfully deleted", Toast.LENGTH_SHORT).show();
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