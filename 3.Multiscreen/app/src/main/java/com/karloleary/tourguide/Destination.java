package com.karloleary.tourguide;

/**
 * Created by karl on 29/03/17.
 */

public class Destination {

    private int DESTINATION_ID = 0;
    private String title = "";
    private int image = 0;


    public Destination(int d, String t, int i) {
        DESTINATION_ID = d;
        title = t;
        image = i;
    }

    public int getId() { return DESTINATION_ID; }

    public String getTitle() {
        return title;
    }

    public int getImage() { return image; }
}
