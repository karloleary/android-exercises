package com.karloleary.tourguide;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.karloleary.multiscreen.R;

import java.util.ArrayList;

/**
 * Created by karl on 29/03/17.
 */

public class DestinationsAdapter extends ArrayAdapter<Destination> {


    public DestinationsAdapter(Activity context, ArrayList<Destination> destinations) {
        super(context, 0, destinations);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null)
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_tourguide_destinations_item, parent, false);

        Destination ddd = getItem(position);

        TextView title = (TextView)itemView.findViewById(R.id.destination_title);
        title.setText(ddd.getTitle());

        ImageView image = (ImageView)itemView.findViewById(R.id.destination_image);
        image.setImageResource(ddd.getImage());

        return itemView;
    }
}
