package com.codepath.apps.mysimpletweets;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TimelineActivity extends ActionBarActivity {

    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        client = TwitterApplication.getRestClient(); // singleton, meaning it uses the same client across all activities.
        populateTimeline();

    }
    // Send API request to get the timeline json
    // Fill the list view by creating the tweet objects from the JSON
    private void populateTimeline(){
        client.getHomeTimeline();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

/*        if(id == R.id.action_settings){
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
