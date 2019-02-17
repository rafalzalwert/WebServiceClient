package com.example.zalwe.webserviceclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    static final String apiEndpoint = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGetPostByUserId = findViewById(R.id.buttonGetActivity);
        Button buttonPost = findViewById(R.id.buttonPostActivity);
        Button buttonPut = findViewById(R.id.buttonPutActivity);
        Button buttonGetPostById = findViewById(R.id.buttonDeleteActivity);

        buttonGetPostById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetActivity.class);
                startActivity(intent);
            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        buttonPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PutActivity.class);
                startActivity(intent);
            }
        });

        buttonGetPostByUserId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
    }
}
