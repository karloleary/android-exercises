package com.karloleary.player;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.karloleary.multiscreen.R;

import static com.karloleary.multiscreen.R.raw.vid_20150818_wa0000;

/**
 * Unable to get the VideoView to work the way I wanted.
 * - Start on click
 * - Intercept loss of audio focus
 * - Play/Pause/Skip on external event
 *
 * @link http://stackoverflow.com/questions/12482203/how-to-create-custom-ui-for-android-mediacontroller/14323144#14323144
 */

public class PlayerActivity extends AppCompatActivity {

    private MediaPlayer player;
    private VideoView video;
    final private String TAG = "PlayerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Log.d(TAG, "step 1");

        player = MediaPlayer.create(this, R.raw.vid_20150818_wa0000);
        VideoView video = (VideoView)findViewById(R.id.video);
        video.setVideoURI(Uri.parse("android.resource://com.karloleary.multiscreen/raw/" + R.raw.vid_20150818_wa0000));
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "video click");
                player.start();
            }
        });



//        final MediaController mediaController = new MediaController(this, true);
//        mediaController.setEnabled(false);

//        video.setMediaController(mediaController);
//        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mediaController.setEnabled(true);
//            }
//        });
////        video.setR
////        MediaPlayer.create(this, R.raw.vid_20150818_wa0000);
    }

    public void play(View v) {
        Log.d(TAG, "play()");

        player.start();
        player.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(PlayerActivity.this, "All gone", Toast.LENGTH_LONG).show();
                    }
                }

        );
    }

    public void pause(View v) {
        if (video != null && video.isPlaying()) {
            video.stopPlayback();
            video = null;
        }
        super.onPause();

//        player.pause();
    }

    public void skip(View v) {
//        int pos = player.getCurrentPosition();
//        player.seekTo(pos + 50000);
    }
}
