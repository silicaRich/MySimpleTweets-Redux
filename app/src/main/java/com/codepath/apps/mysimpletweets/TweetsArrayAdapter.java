package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by floko_000 on 8/4/2016.
 */

// Take Tweet objects and turn them into Views to be displayed in ListView
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    Typeface helvetica;
    private Context context;


    public TweetsArrayAdapter(Context context, List<Tweet> tweets){
        super(context, 0, tweets);
        this.context = context;
        helvetica = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue_Med.ttf");

    }
    // Instead of simple_list_item_1, using 0 as paramater and set up custom template.
    public View getView(int position, View convertView, ViewGroup parent){


        // 1. Get the tweet
        Tweet tweet = getItem(position);
        // 2. Find or inflate the template
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        // 3. Find the subview to fill with data in the template
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        // tvBody.setTypeface(helvetica);
        TextView tvCreatedAt = (TextView) convertView.findViewById(R.id.tvCreatedAt);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvFavouritesCount = (TextView) convertView.findViewById(R.id.tvFavouritesCount);
        // Populate data into subviews
        tvUserName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvCreatedAt.setText(tweet.getCreatedAt());
        tvName.setText(tweet.getUser().getName());
        tvFavouritesCount.setText(tweet.getFavouritesCount());
        ivProfileImage.setImageResource(android.R.color.transparent); // clear out old image for recycled view
        ivProfileImage.setTag(tweet.getUser().getScreenName());
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);

        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("screen_name", v.getTag().toString());
                       context.startActivity(intent);
            }
        });


        // 5. Return the view to be inserted into the list
        return convertView;
    }

}
