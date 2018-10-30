package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.androidlibjokes.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String STRING_JOKE = "string_joke";
    private ProgressBar spinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        spinner = root.findViewById(R.id.progressBar);
        Button mButton = root.findViewById(R.id.joke_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        spinner.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(getActivity(), JokeActivity.class);
                        intent.putExtra(STRING_JOKE, output);
                        startActivity(intent);
                    }
                }, new EndpointsAsyncTask.AsyncRequest() {
                    @Override
                    public void processStart() {
                        spinner.setVisibility(View.VISIBLE);
                    }
                }).execute();
            }
        });

        return root;
    }
}
