package com.karloleary.tourguide;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.karloleary.multiscreen.R;


public class DetailsActivity extends AppCompatActivity {

    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourguide_details);

        final Feature fff = DataHandler.getFeature(getIntent().getIntExtra("FEATURE_ID", 0));

        ImageView imageView = (ImageView)findViewById(R.id.feature_image);
        imageView.setImageResource(fff.getImage());
        imageView.setVisibility(View.VISIBLE);

        TextView titleText = (TextView)findViewById(R.id.feature_title);
        titleText.setText(fff.getTitle());

        TextView bioText = (TextView)findViewById(R.id.feature_bio);
        bioText.setText(fff.getBio());

        TextView addressText = (TextView)findViewById(R.id.feature_address);
        addressText.setText(fff.getAddress());

        TextView twitterText = (TextView)findViewById(R.id.feature_twitter);
        twitterText.setText(fff.getTwitter());

        View twitterLink = (View)findViewById(R.id.twitter_link);
        twitterLink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent iii =new Intent(Intent.ACTION_VIEW);
               iii.setData(Uri.parse("https://twitter.com/"+fff.getTwitter()));
               startActivity(iii);
           }
        });

        final VideoView video = (VideoView)findViewById(R.id.feature_video);

        releasePlayer();
        if (fff.getVideo() == 0) {
            video.setVisibility(View.GONE);
        }
        else {
            video.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus)
                        video.stopPlayback();
                }

            });
            video.setVisibility(View.VISIBLE);

            final MediaController mediaController = new MediaController(this, true);
            mediaController.setEnabled(false);

            video.setMediaController(mediaController);

            video.setVideoURI(Uri.parse("android.resource://com.karloleary.multiscreen/raw/" + fff.getVideo()));
            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaController.setEnabled(true);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    public void releasePlayer() {
        if (video != null && video.isPlaying())
            video.stopPlayback();
    }
}
