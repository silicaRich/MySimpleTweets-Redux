package com.codepath.apps.mysimpletweets;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.mysimpletweets.fragments.TweetsListFragment;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TweetsListFragment fragmentTweetsList;
    private TwitterClient client;
    public long max_id;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
        startActivityForResult(i, 200);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);
        client = TwitterApplication.getRestClient();
        populateTimeline(-1);
        if(savedInstanceState == null) {
            // Access fragment
            fragmentTweetsList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        max_id = 1;
     //   tweets.clear();
     //   aTweets.notifyDataSetChanged();
        populateTimeline(1);
    }

    private void populateTimeline(int page){
        client.getHomeTimeline(max_id, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                // ArrayList<Tweet> tweets = Tweet.fromJSONArray(json);
                fragmentTweetsList.addAll(Tweet.fromJSONArray(json));
                Log.d("DEBUG", fragmentTweetsList.toString());
            //    max_id = aTweets.getItem(aTweets.getCount()-1).getUid();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }

        });
    }

}
