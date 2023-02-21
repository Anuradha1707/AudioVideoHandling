package com.sample.videoaudiolist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Integer> media= new ArrayList<>();
    private MediaController mediac;
    private VideoView video;
    private TextView videoview,audioview;
    int count=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoview= (TextView)findViewById(R.id.textVideo);
        audioview= (TextView)findViewById(R.id.textAuido);
        videoview.setBackgroundColor(getResources().getColor(R.color.darkblue));
        audioview.setBackgroundColor(getResources().getColor(R.color.lightorg));
        // create list
        media.add(R.raw.vavenger);
        media.add(R.raw.action);
        media.add(R.raw.vboy);
        media.add(R.raw.vhulk);
        media.add(R.raw.vcomeback);
        media.add(R.raw.vfight);
        media.add(R.raw.thor);

        //set videoview
        video= (VideoView) findViewById(R.id.video);
        video.setVideoPath("android.resource://"+getPackageName()+"/"+media.get(count));
        mediac = new MediaController(this);
        mediac.setAnchorView(video);
        video.setMediaController(mediac);
        //
        video.start();
        count++;
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //auto play next video after completion
                video.setVideoPath("android.resource://" + getPackageName() + "/" +media.get(count));
                video.start();
                count++;
            }
        });
        mediac.setPrevNextListeners(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // for playing next
                                            if(count<4){
                                            video.setVideoPath("android.resource://" + getPackageName() + "/" + media.get(count));
                                            video.start();
                                            count++;}
                                            else{
                                                Toast.makeText(MainActivity.this, "Not having more videos", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //for playing previous
                        if(count>0) {
                            count--;
                            video.setVideoPath("android.resource://" + getPackageName() + "/" + media.get(count));
                            video.start();
                        }else
                            Toast.makeText(MainActivity.this, "Not having more videos", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    public void audio(View view) {
        videoview.setBackgroundColor(getResources().getColor(R.color.ligthblue));

        audioview.setBackgroundColor(getResources().getColor(R.color.darkorg));

        Intent inte = new Intent(this,Audio.class);
        startActivity(inte);
    }
}