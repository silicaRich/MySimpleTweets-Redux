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

    public String getScreenName() {
        return screenName;
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


    // deserialize user json into User object

    public static User fromJSON(JSONObject jsonObject){
        User u = new User();

        try {
            u.name = jsonObject.getString("name");
            u.uid = jsonObject.getLong("id");
            u.screenName = jsonObject.getString("screen_name");
            u.profileImageUrl = jsonObject.getString("profile_banner_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }
}
