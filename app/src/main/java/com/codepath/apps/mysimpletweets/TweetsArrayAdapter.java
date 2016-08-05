package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.codepath.apps.mysimpletweets.models.Tweet;

import java.util.List;

/**
 * Created by floko_000 on 8/4/2016.
 */

// Take Tweet objects and turn them into Views to be displayed in ListView
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets){
        super(context, android.R.layout.simple_list_item_1, tweets);

    }
    // later will want to overrise simple_list_item_1 and set up custom.

}
