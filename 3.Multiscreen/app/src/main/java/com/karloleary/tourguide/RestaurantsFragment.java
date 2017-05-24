package com.karloleary.tourguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.karloleary.multiscreen.R;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int destination_id = getActivity().getIntent().getIntExtra("DESTINATION_ID", 0);
        SharedPreferences prefs = getActivity().getPreferences(MODE_PRIVATE);
        if (destination_id == 0)
            destination_id = prefs.getInt("DESTINATION_ID", 1);
        else
            prefs.edit().putInt("DESTINATION_ID", destination_id).commit();

        final ArrayList<Feature> features = DataHandler.getRestaurants(destination_id);
        View rootView;
        if (features.size() > 0) {
            rootView = inflater.inflate(R.layout.activity_tourguide_features_fragment, container, false);
            FeaturesAdapter adapter = new FeaturesAdapter(getActivity(), features);

            ListView listView = (ListView) rootView.findViewById(R.id.features_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Feature fff = features.get(position);

                    SharedPreferences prefs = getActivity().getPreferences(MODE_PRIVATE);
                    prefs.edit().putInt("FEATURE_TYPE", 0).commit();

                    Intent iii = new Intent(getActivity(), DetailsActivity.class);
                    iii.putExtra("FEATURE_ID", fff.getId());
                    startActivity(iii);
                }
            });
        }
        else {
            rootView = inflater.inflate(R.layout.activity_tourguide_features_empty, container, false);
        }

        return rootView;
    }
}
