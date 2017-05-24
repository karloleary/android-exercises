package com.karloleary.multiscreen;

/**
 * Created by karl on 29/03/17.
 */

public class Word {

    private String miwok    = "";
    private String english  = "";
    private int image       = 0;
    private int audio       = 0;


    public Word(String m, String e, int a) {
        miwok   = m;
        english = e;
        audio   = a;
    }

    public Word(String m, String e, int i, int a) {
        miwok   = m;
        english = e;
        image   = i;
        audio   = a;
    }

    public String getMiwokTranslation() {
        return miwok;
    }

    public String getDefaultTranslation() {
        return english;
    }

    public int getImageResourceId() {
        return image;
    }

    public int getAudioResourceId() {
        return audio;
    }
}
