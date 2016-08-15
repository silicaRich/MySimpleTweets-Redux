package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by floko_000 on 8/4/2016.
 */
public class User {

    private String name;
    private long uid;
    private String profileImageUrl;
    private String screenName;
    private int followers;
    private int following;
    private String tagline;

    public String getScreenName() {
        return screenName;
    }

    public String getTagline() {
        return tagline;
    }

    public long getUid() {
        return uid;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public int getFollowersCount() {
        return followers;
    }

    public int getFriendsCount() {
        return following;
    }


    // deserialize user json into User object

    public static User fromJSON(JSONObject jsonObject){
        User u = new User();

        try {
            u.name = jsonObject.getString("name");
            u.uid = jsonObject.getLong("id");
            u.screenName = jsonObject.getString("screen_name");
            u.profileImageUrl = jsonObject.getString("profile_image_url");
            u.tagline = jsonObject.getString("description");
            u.followers = jsonObject.getInt("followers_count");
            u.following = jsonObject.getInt("friends_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }
}
