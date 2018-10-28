package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidlibjokes.JokeActivity;
import com.example.javajokes.Jokes;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String STRING_JOKE = "string_joke";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button mButton = root.findViewById(R.id.joke_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jokes jokes = new Jokes();

                new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        Toast.makeText(getActivity(), output, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), JokeActivity.class);
                        intent.putExtra(STRING_JOKE, output);
                        startActivity(intent);
                    }
                }).execute(new Pair<Context, String>(getContext(), jokes.getJoke()));
            }
        });

        return root;
    }
}
