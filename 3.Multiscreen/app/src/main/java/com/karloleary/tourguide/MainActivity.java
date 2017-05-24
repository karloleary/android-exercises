package com.karloleary.tourguide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.karloleary.multiscreen.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourguide_destinations);

        final ArrayList<Destination> list = DataHandler.getDestinations();

        DestinationsAdapter adapter = new DestinationsAdapter(this, list);
        ListView listView = (ListView)findViewById(R.id.destinations_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Destination ddd = list.get(position);

                SharedPreferences prefs = MainActivity.this.getPreferences(MODE_PRIVATE);
                prefs.edit().putInt("DESTINATION_ID", ddd.getId()).commit();

                Intent iii = new Intent(MainActivity.this, FeaturesActivity.class);
                iii.putExtra("DESTINATION_ID", ddd.getId());
                iii.putExtra("FEATURE_TYPE", 0);
                startActivity(iii);
            }
        });
    }

}
