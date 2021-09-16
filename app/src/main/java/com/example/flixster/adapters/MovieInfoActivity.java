package com.example.flixster.adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.MainActivity;
import com.example.flixster.R;

public class MovieInfoActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvOverview;
    TextView tvRating;
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        tvRating = findViewById(R.id.tvRating);
        ivPoster = findViewById(R.id.ivPoster);
        getSupportActionBar().setTitle("Movie Info");
        Intent passedIntent = getIntent();
        tvTitle.setText(passedIntent.getStringExtra(MainActivity.MOVIE_TITLE));
        Glide.with(this).load(passedIntent.getStringExtra(MainActivity.MOVIE_URL)).into(ivPoster);
        tvRating.setText("Rating: " + passedIntent.getIntExtra(MainActivity.MOVIE_RATING, 0) + "/10");
        tvOverview.setText(passedIntent.getStringExtra(MainActivity.MOVIE_OVERVIEW));
    }
}