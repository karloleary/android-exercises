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


public class FamilyFragment extends Fragment {

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


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("әpә",       "father",           R.drawable.family_father,           R.raw.family_father));
        words.add(new Word("әṭa",       "mother",           R.drawable.family_mother,           R.raw.family_mother));
        words.add(new Word("angsi",     "son",              R.drawable.family_son,              R.raw.family_son));
        words.add(new Word("tune",      "daughter",         R.drawable.family_daughter,         R.raw.family_daughter));
        words.add(new Word("taachi",    "older brother",    R.drawable.family_older_brother,    R.raw.family_older_brother));
        words.add(new Word("chalitti",  "younger brother",  R.drawable.family_younger_brother,  R.raw.family_younger_brother));
        words.add(new Word("teṭe",      "older sister",     R.drawable.family_older_sister,     R.raw.family_older_sister));
        words.add(new Word("kolliti",   "younger sister",   R.drawable.family_younger_sister,   R.raw.family_younger_sister));
        words.add(new Word("ama",       "grandmother",      R.drawable.family_grandmother,      R.raw.family_grandmother));
        words.add(new Word("paapa",     "grandfather",      R.drawable.family_grandfather,      R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.family_colour);
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
