package com.st00.afir.musicplayerdemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button playMusic, pauseMusic,increaseVolume, decreaseVolume, goToMiddle;
    //creating a mediaPlayer
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    int duration = 0;
    int volume = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playMusic = (Button) findViewById(R.id.playButton);
        pauseMusic = (Button) findViewById(R.id.pauseButton);

        increaseVolume = (Button) findViewById(R.id.plusButton);
        decreaseVolume = (Button) findViewById(R.id.minusButton);

        goToMiddle = (Button) findViewById(R.id.goToButton);

        mediaPlayer = MediaPlayer.create(this, R.raw.chauffeur_capable);
        duration = mediaPlayer.getDuration();

        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        pauseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d("Main","volume est :"+volume);

        increaseVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volume++;
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
                Log.d("Main","volume incrémenté :"+volume);
            }
        });

        decreaseVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volume--;
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
                Log.d("Main","volume décrementé :"+volume);
            }
        });

        goToMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(duration/2);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getApplicationContext(),"I'm done !",Toast.LENGTH_LONG).show();
            }
        });
    }
}
