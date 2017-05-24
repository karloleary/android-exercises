package com.karloleary.multiscreen;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.karloleary.tourguide.Destination;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColoursFragment extends Fragment {

    private MediaPlayer player;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releasePlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener changeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ) {
                player.pause();
                player.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                player.release();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                player.start();
            }
        }
    };


    public ColoursFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("weṭeṭṭi",   "red",              R.drawable.color_red,               R.raw.color_red));
        words.add(new Word("chokokki",  "green",            R.drawable.color_green,             R.raw.color_green));
        words.add(new Word("ṭakaakki",  "brown",            R.drawable.color_brown,             R.raw.color_brown));
        words.add(new Word("ṭopoppi",   "gray",             R.drawable.color_gray,              R.raw.color_gray));
        words.add(new Word("kululli",   "black",            R.drawable.color_black,             R.raw.color_black));
        words.add(new Word("kelelli",   "white",            R.drawable.color_white,             R.raw.color_white));
        words.add(new Word("ṭopiisә",   "dusty yellow",     R.drawable.color_dusty_yellow,      R.raw.color_dusty_yellow));
        words.add(new Word("chiwiiṭә",  "mustard yellow",   R.drawable.color_mustard_yellow,    R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.colours_colour);
        ListView listView = (ListView)rootView.findViewById(R.id.words_listview);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releasePlayer();

                int result = audioManager.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    player.start();
                    player.setOnCompletionListener(completionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player == null)
            return;

        player.release();
        player = null;

        audioManager.abandonAudioFocus(changeListener);
    }
}
