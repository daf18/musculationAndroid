package com.example.musculaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musculaction.model.Exercice;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciceDetails extends YouTubeBaseActivity {

YouTubePlayerView youTubePlayerView;
YouTubePlayer.OnInitializedListener onInitializedListener;
TextView details_tv, title_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_details);

        youTubePlayerView = findViewById(R.id.youtubePlayerView);
        details_tv = findViewById(R.id.details_tv);
        title_tv = findViewById(R.id.title_tv);

        //get exercice that was clicked on
        Exercice exercice = getIntent().getExtras().getParcelable("exercice");
        //transform youtubeUrl into youtubeId
        String youtubeId = getYouTubeId(exercice.getYoutubeUrl());

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(youtubeId);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(),"Loading failed",Toast.LENGTH_SHORT).show();
            }
        };
        youTubePlayerView.initialize("AIzaSyCr0TIaJ-th42xlF3GPG9kD19tOvKU0P2E",onInitializedListener);
        title_tv.setText(exercice.getTitle());
        details_tv.setText(exercice.getDetails());
    }
    //youtubeUrl to youtubeId
    private String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }
}