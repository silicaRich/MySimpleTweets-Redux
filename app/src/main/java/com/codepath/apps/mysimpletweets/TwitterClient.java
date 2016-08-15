package com.codepath.apps.mysimpletweets;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 */

public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "RPiBcVxVoeNFzwe1i7VQihqb9";       // Change this
	public static final String REST_CONSUMER_SECRET = "vecARvRVEVJtUqktoFfdIvJ4zrImwttkKJt5VmyMv0Q2uJLOWg"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	public void getHomeTimeline(long max_id, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/home_timeline.json");
		RequestParams params = new RequestParams();
		params.put("count", 6);
		if (max_id > 1){
			params.put("max_id", max_id);
		}
		else {
			params.put("since_id", 1);
		}
		getClient().get(apiUrl, params, handler);
	}

	public void postTweet(JsonHttpResponseHandler handler, String tweet) {

		String apiUrl = getApiUrl("statuses/update.json");
		RequestParams params = new RequestParams();
		params.put("status", tweet);
		getClient().post(apiUrl, params, handler);
	}


	public void getMentionsTimeline(long max_id, JsonHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		RequestParams params = new RequestParams();
		params.put("count", 6);
		if (max_id > 1){
			params.put("max_id", max_id);
		}
		else {
			params.put("since_id", 1);
		}
		getClient().get(apiUrl, params, handler);

	}

	public void getUserTimeline(JsonHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		RequestParams params = new RequestParams();
		params.put("count", 25);
		getClient().get(apiUrl, params, handler);
	}

	public void getUserInfo(JsonHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/verify_credentials.json");
		RequestParams params = new RequestParams();
		getClient().get(apiUrl, null, handler);
	}

}