package com.karloleary.tourguide;

/**
 * Created by karl on 27/04/17.
 */

public class Feature {

    private int DESTINATION_ID;
    private int FEATURE_ID;
    private String title;
    private String bio;
    private String address;
    private String twitter;
    private int image;
    private int video;

    public Feature() {}

    public Feature(int d, int f, String t, String b, String a, String w, int i) {
        DESTINATION_ID = d;
        FEATURE_ID = f;
        title= t;
        bio= b;
        address = a;
        twitter = w;
        image = i;
    }

    public Feature(int d, int f, String t, String b, String a, String w, int i, int v) {
        DESTINATION_ID = d;
        FEATURE_ID = f;
        title= t;
        bio= b;
        address = a;
        twitter = w;
        image = i;
        video = v;
    }

    public int getId() { return FEATURE_ID; }

    public int getDestinationId() { return DESTINATION_ID; }

    public String getTitle() {
        return title;
    }

    public String getBio() {
        return bio;
    }

    public String getAddress() {
        return address;
    }

    public String getTwitter() { return twitter; }

    public int getImage() { return image; }

    public int getVideo() { return video; }
}
