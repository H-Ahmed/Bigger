package com.example.androidlibjokes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javajokes.Jokes;

public class JokeActivity extends AppCompatActivity {

    private static final String STRING_JOKE = "string_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView textView = findViewById(R.id.joke_text_view);

        Intent intent = getIntent();
        if (intent.hasExtra(STRING_JOKE)){
            textView.setText(intent.getStringExtra(STRING_JOKE));
        }else {
            Jokes jokes = new Jokes();


//        new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
//            @Override
//            public void processFinish(String output) {
//                Toast.makeText(JokeActivity.this, output, Toast.LENGTH_SHORT).show();
//            }
//        }).execute(new Pair<Context, String>(this, jokes.getJoke()));

            textView.setText(jokes.getJoke());
        }

    }
}
