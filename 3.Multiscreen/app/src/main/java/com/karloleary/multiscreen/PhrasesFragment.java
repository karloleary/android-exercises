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
public class PhrasesFragment extends Fragment {

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


    public PhrasesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words= new ArrayList<Word>();
        words.add(new Word("minto wuksus",      "Where are you going?", R.raw.phrase_where_are_you_going));
        words.add(new Word("tinnә oyaase'nә",   "What is your name?",   R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset...",        "My name is...",        R.raw.phrase_my_name_is));
        words.add(new Word("michәksәs?",        "How are you feeling?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("kuchi achit",       "I’m feeling good.",    R.raw.phrase_im_feeling_good));
        words.add(new Word("әәnәs'aa?",         "Are you coming?",      R.raw.phrase_are_you_coming));
        words.add(new Word("hәә’ әәnәm",        "Yes, I’m coming.",     R.raw.phrase_yes_im_coming));
        words.add(new Word("әәnәm",             "I’m coming.",          R.raw.phrase_im_coming));
        words.add(new Word("yoowutis",          "Let’s go.",            R.raw.phrase_lets_go));
        words.add(new Word("әnni'nem",          "Come here.",           R.raw.phrase_come_here));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.phrases_colour);
        ListView listView = (ListView)rootView.findViewById(R.id.words_listview);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word= words.get(position);
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
