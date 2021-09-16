package com.example.flixster;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.ComplexMovieAdapter;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.adapters.MovieInfoActivity;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=ENTER_API_KEY_HERE";
    public static final String TAG="MainActivity";
    List<Movie> movies;

    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_OVERVIEW = "movie_overview";
    public static final String MOVIE_URL = "movie_url";
    public static final String MOVIE_RATING = "movie_rating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();
        //final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        ComplexMovieAdapter.OnClickListener onClickListener = new ComplexMovieAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(MainActivity.this, MovieInfoActivity.class);
                intent.putExtra(MOVIE_TITLE, movies.get(position).getTitle());
                intent.putExtra(MOVIE_OVERVIEW, movies.get(position).getOverview());
                intent.putExtra(MOVIE_URL, movies.get(position).getBackdropPath());
                intent.putExtra(MOVIE_RATING, movies.get(position).getRating());
                startActivity(intent);
            }
        };

        final ComplexMovieAdapter complexMovieAdapter = new ComplexMovieAdapter(this, movies, onClickListener);
        rvMovies.setAdapter(complexMovieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {

                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    complexMovieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movies.size());
                } catch (JSONException e) {
                    Log.d(TAG, "Hit json exception " + e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                Log.d(TAG, "onFailure");
            }
        });
    }
}