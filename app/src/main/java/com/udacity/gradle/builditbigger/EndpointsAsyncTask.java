package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public interface AsyncRequest{
        void processStart();
    }
    public AsyncRequest mStart;

    public interface AsyncResponse{
        void processFinish(String output);
    }
    public AsyncResponse mDelegate;

    EndpointsAsyncTask(AsyncResponse delegate, AsyncRequest start){
        mDelegate = delegate;
        mStart = start;
    }


    private static MyApi myApiService = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mStart.processStart();
    }

    @Override
    protected String doInBackground(Void...param) {
        EspressoIdlingResource.increment();
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }



        try {
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: "+ e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        EspressoIdlingResource.decrement();
        mDelegate.processFinish(result);
    }
}
