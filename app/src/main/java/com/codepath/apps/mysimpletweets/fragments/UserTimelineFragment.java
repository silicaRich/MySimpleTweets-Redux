package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by floko_000 on 8/12/2016.
 */
public class UserTimelineFragment extends TweetsListFragment implements AbsListView.OnScrollListener {

    private TwitterClient client;
    public long max_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline(-1);
    }

        public static UserTimelineFragment newInstance(String screen_name) {
            UserTimelineFragment userFragment = new UserTimelineFragment();
            Bundle args = new Bundle();
            args.putString("screen_name", screen_name);
            userFragment.setArguments(args);
            return userFragment;
        }

    @Override
    public void populateTimeline(int page){
        String screenName = getArguments().getString("screen_name");

        client.getUserTimeline(max_id, screenName, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                ArrayList<Tweet> tweets = Tweet.fromJSONArray(json);
                addAll(Tweet.fromJSONArray(json));
                max_id = aTweets.getItem(aTweets.getCount()-1).getUid();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }

        });
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
