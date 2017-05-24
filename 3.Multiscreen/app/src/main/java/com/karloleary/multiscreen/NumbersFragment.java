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

import java.util.ArrayList;


public class NumbersFragment extends Fragment {

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


    public NumbersFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("lutti",     "one",      R.drawable.number_one,      R.raw.number_one));
        words.add(new Word("otiiko",    "two",      R.drawable.number_two,      R.raw.number_two));
        words.add(new Word("tolookosu", "three",    R.drawable.number_three,    R.raw.number_three));
        words.add(new Word("oyyisa",    "four",     R.drawable.number_four,     R.raw.number_four));
        words.add(new Word("massokka",  "five",     R.drawable.number_five,     R.raw.number_five));
        words.add(new Word("temmokka",  "six",      R.drawable.number_six,      R.raw.number_six));
        words.add(new Word("kenekaku",  "seven",    R.drawable.number_seven,    R.raw.number_seven));
        words.add(new Word("kawinta",   "eight",    R.drawable.number_eight,    R.raw.number_eight));
        words.add(new Word("wo'e",      "nine",     R.drawable.number_nine,     R.raw.number_nine));
        words.add(new Word("na'aacha",  "ten",      R.drawable.number_ten,      R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.numbers_colour);
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
