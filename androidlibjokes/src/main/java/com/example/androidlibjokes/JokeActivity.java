package com.example.androidlibjokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    private static final String STRING_JOKE = "string_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView textView = findViewById(R.id.joke_text_view);

        Intent intent = getIntent();
        if (intent.hasExtra(STRING_JOKE)){
            Toast.makeText(this, intent.getStringExtra(STRING_JOKE), Toast.LENGTH_SHORT).show();
            textView.setText(intent.getStringExtra(STRING_JOKE));
        }else {
            finish();
        }

    }
}
