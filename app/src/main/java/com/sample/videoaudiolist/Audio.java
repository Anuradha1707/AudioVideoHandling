package com.sample.videoaudiolist;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Audio extends AppCompatActivity implements Runnable {
    private MediaPlayer mp;
    private ImageView play, previous, next;
    private SeekBar seek;
    private List<Integer> allAudio = new ArrayList<>();
    int count=1, duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        play=(ImageView)findViewById(R.id.play);
        previous= (ImageView)findViewById(R.id.previous);
        next= (ImageView) findViewById(R.id.next);
        allAudio.add(R.raw.aboss);
        allAudio.add(R.raw.ahalloween);
        allAudio.add(R.raw.arunning);
        allAudio.add(R.raw.ghost);
        allAudio.add(R.raw.raindrum);
        allAudio.add(R.raw.swing);
        mp = MediaPlayer.create(this,allAudio.get(0));
        Log.i("value", String.valueOf(allAudio.get(0)));

        seek= (SeekBar) findViewById(R.id.seekBar);
        seek.setProgress(0);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seek value",String.valueOf(progress));
                //mp.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(seek.getProgress());
            }
        });

    }

    public void previousClick(View view) {
        if(count>0){
            mp.release();
            count--;
            mp=MediaPlayer.create(this,allAudio.get(count));
            Log.i("value", String.valueOf(allAudio.get(count)));
            Log.i("count", String.valueOf(count));
            this.pause(view);
        }
        duration=mp.getDuration();
        seek.setMax(duration);
        Log.i("test","Chal ja re mat pareshan kar");

        Log.i("test",String.valueOf(duration));
    }

    public void pause(View view) {
        seek.setMax(mp.getDuration());
        if(mp.isPlaying()){
            mp.pause();
            play.setImageResource(android.R.drawable.ic_media_play);
        }
        else{
            mp.start();
            play.setImageResource(android.R.drawable.ic_media_pause);
        }
        this.setSeek();
        new Thread(this).start();
    }

    public void next(View view) {
        if(count<4){
            mp.release();
            mp=MediaPlayer.create(this,allAudio.get(count));
            Log.i("value", String.valueOf(allAudio.get(count)));
            this.pause(view);
            duration=mp.getDuration();
            seek.setMax(duration);
            Log.i("test","Chal ja re mat pareshan kar");

            Log.i("test",String.valueOf(duration));
            Log.i("count", String.valueOf(count));
            count++;

        }
    }
    public void setSeek(){
        seek.setProgress(mp.getCurrentPosition());
    }
   public void run(){
        while(mp!=null){
            try{
                Thread.sleep(1000);
                duration=mp.getCurrentPosition();

            }catch(Exception e){
                Log.i("Exception",String.valueOf(e));
                return;
            }
            seek.setProgress(duration);
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public void video(View view) {
        Intent inte= new Intent(this, MainActivity.class);
        startActivity(inte);
    }
}