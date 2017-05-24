package com.karloleary.tourguide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.karloleary.multiscreen.R;

import java.util.ArrayList;

/**
 * Created by karl on 27/04/17.
 */

public class FeaturesAdapter extends ArrayAdapter<Feature> {


    public FeaturesAdapter(Activity context, ArrayList<Feature> features) {
        super(context, 0, features);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null)
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_tourguide_features_item, parent, false);

        Feature fff = getItem(position);

        TextView titleView = (TextView)itemView.findViewById(R.id.feature_title);
        titleView.setText(fff.getTitle());

        TextView bioView = (TextView)itemView.findViewById(R.id.feature_bio);
        bioView.setText(fff.getBio());

        ImageView imageView = (ImageView) itemView.findViewById(R.id.feature_image);
        imageView.setImageResource(fff.getImage());
        imageView.setVisibility(View.VISIBLE);

        return itemView;
    }
}

