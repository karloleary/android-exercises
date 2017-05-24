package com.karloleary.tourguide;

import android.util.Log;

import com.karloleary.multiscreen.R;
import java.util.ArrayList;


/**
 * Created by karl on 30/04/17.
 */

public class DataHandler {

    private static ArrayList<Destination> destinations;
    private static ArrayList<Feature> restaurants;
    private static ArrayList<Feature> events;
    
    private static void populate() {
        destinations = new ArrayList<>();
        destinations.add(new Destination(1, "Cork, Ireland",        R.drawable.tourguide_cork));
        destinations.add(new Destination(2, "Dublin, Ireland",      R.drawable.tourguide_dublin));
        destinations.add(new Destination(3, "London, UK",           R.drawable.tourguide_london));
        destinations.add(new Destination(4, "Paris, France",        R.drawable.tourguide_paris));
        destinations.add(new Destination(5, "Dubrovnik, Croatia",   R.drawable.tourguide_dubrovnik));

        restaurants = new ArrayList<>();
        restaurants.add(new Feature(1, 1, "English Market",         "The best pace for good food.", "Cork City, Ireland", "englishmarket", R.drawable.tourguide_cork_englishmarket, R.raw.vid_20150818_wa0000));
        restaurants.add(new Feature(1, 2, "The Ambassador",         "Good Chineese.", "Cork City, Ireland", "theambassador", R.drawable.tourguide_cork_theambassador));
        restaurants.add(new Feature(1, 3, "Elbow Lane",             "The best pace for a good steak.", "Cork City, Ireland", "elbowlane", R.drawable.tourguide_cork_elbowlane));
        restaurants.add(new Feature(1, 4, "Star Anise",             "French food.", "Cork City, Ireland", "staranise", R.drawable.tourguide_cork_staranise));
        restaurants.add(new Feature(2, 5, "Bon Appitite",           "Good Fish", "Malahide, Dublin, Ireland", "bonappitite", R.drawable.tourguide_dublin_bonappetit));
        restaurants.add(new Feature(3, 6, "The Ledbury",            "Good food", "Hyde Park, London", "theledbury", R.drawable.tourguide_london_theledbury));
        restaurants.add(new Feature(4, 7, "Alliance",               "Fine Dining", "Paris France", "alliance", R.drawable.tourguide_paris_alliance));

        events = new ArrayList<>();
        events.add(new Feature(1, 8,    "Poision Garden",           "Fight against suicide", "Cork City, Ireland", "poisiongarden", R.drawable.tourguide_cork_poisiongarden));
        events.add(new Feature(1, 9,    "Blarney Stone",            "All the best produce Cork has to offer.", "Cork City, Ireland", "blarney", R.drawable.tourguide_cork_blarneystone));
        events.add(new Feature(1, 10,   "Mizen Head",               "All the best produce Cork has to offer.", "Cork City, Ireland", "mizenhead", R.drawable.tourguide_cork_mizenhead));
        events.add(new Feature(2, 11,   "Jameson Distillery",       "Good moives", "Dublin, Ireland", "jamesondistillery", R.drawable.tourguide_dublin_jameson));
    }
    
    static ArrayList getDestinations() {
        populate();
        return destinations;
    }

    static ArrayList getRestaurants(int destination_id) {
        populate();
        ArrayList<Feature> list = new ArrayList<>();
        for (Feature fff: restaurants)
            if (fff.getDestinationId() == destination_id)
                list.add(fff);
        return list;
    }

    static ArrayList getEvents(int destination_id) {
        populate();
        ArrayList<Feature> list = new ArrayList<>();
        for (Feature fff: events)
            if (fff.getDestinationId() == destination_id)
                list.add(fff);
        return list;
    }

    static Feature getFeature(int feature_id) {
        populate();
        for (Feature fff: restaurants)
            if (fff.getId() == feature_id)
                return fff;
        for (Feature fff: events)
            if (fff.getId() == feature_id)
                return fff;
        return new Feature();
    }
}
