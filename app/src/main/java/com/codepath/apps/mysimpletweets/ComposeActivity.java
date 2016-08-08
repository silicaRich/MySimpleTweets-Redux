package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    EditText etNewTweet;
    Button btnTweet;
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etNewTweet = (EditText) findViewById(R.id.etNewTweet);
        btnTweet = (Button) findViewById(R.id.btnTweet);
        client = TwitterApplication.getRestClient();

        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTweet = etNewTweet.getText().toString();

                client.postTweet(new JsonHttpResponseHandler() {
                                     @Override
                                     public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                                         Intent data = new Intent();
                                         data.putExtra("code", 200); // ints work too
                                         // Activity finished ok, return the data
                                         setResult(RESULT_OK, data); // set result code and bundle data for response
                                         finish();
                                     }

                                     @Override
                                     public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                         Log.d("DEBUG", errorResponse.toString());
                                     }
                                 }
                        , newTweet);

                Intent data = new Intent();
                data.putExtra("code", 200); // ints work too
                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish();
            }
        });
    }
}
