package com.example.androidlibjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.javajokes.Jokes;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Jokes jokes = new Jokes();
        TextView textView = findViewById(R.id.joke_text_view);
        textView.setText(jokes.getJoke());

    }
}
