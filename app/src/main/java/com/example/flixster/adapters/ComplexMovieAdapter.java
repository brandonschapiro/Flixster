package com.example.flixster.adapters;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.models.Movie;

import java.util.List;

public class ComplexMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<Movie> movies;
    OnClickListener onClickListener;

    private final int DEFAULT = 0;
    private final int POPULAR = 1;

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    public ComplexMovieAdapter(Context c, List<Movie> m, OnClickListener ocl){
        context = c;
        movies = m;
        onClickListener = ocl;
    }

    //A movie will use the popular movie layout if it is rated higher than 7.5 / 10, if it is rated lower then it will use the default movie layout
    public int getItemViewType(int position){
        if(movies.get(position).getRating() >= 7.5){
            return POPULAR;
        }
        else{
            return DEFAULT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);

        //Determines which layout to inflate based off of the ViewGroup's viewType
        switch(viewType) {
            case POPULAR:
                View popularView;
                popularView = inflater.inflate(com.example.flixster.R.layout.item_movie_popular, parent, false);
                viewHolder = new ViewHolderPopular(popularView);
                break;
            default:
                View view = inflater.inflate(com.example.flixster.R.layout.item_movie, parent, false);
                viewHolder = new ViewHolderDefault(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //configure methods bind the data to the ViewHolder based off of the viewType
        switch(holder.getItemViewType()){
            case POPULAR:
                ViewHolderPopular viewHolderPopular = (ViewHolderPopular) holder;
                configurePopularViewHolder(viewHolderPopular, position);
                break;
            default:
                ViewHolderDefault viewHolderDef = (ViewHolderDefault) holder;
                configureDefaultViewHolder(viewHolderDef, position);
                break;
        }
    }

    private void configurePopularViewHolder(ViewHolderPopular vhp, int position){
        Movie movie = movies.get(position);
        if(movie != null){
            String imageUrl = movie.getBackdropPath();
            vhp.getTvTitle().setText(movie.getTitle());
            Glide.with(context).load(imageUrl).into(vhp.getIvPoster());
            vhp.getIvPoster().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClicked(position);
                }
            });
        }
    }

    private void configureDefaultViewHolder(ViewHolderDefault vhd, int position){
        Movie movie = movies.get(position);
        if(movie != null){
            String imageUrl = movie.getPosterPath();
            vhd.getTvTitle().setText(movie.getTitle());
            vhd.getTvOverview().setText(movie.getOverview());
            Glide.with(context).load(imageUrl).into(vhd.getIvPoster());
            vhd.getIvPoster().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClicked(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
