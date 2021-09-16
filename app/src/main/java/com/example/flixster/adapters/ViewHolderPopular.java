package com.example.flixster.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;

//ViewHolder class for a popular movie
public class ViewHolderPopular extends RecyclerView.ViewHolder{

    TextView tvTitle;
    ImageView ivPoster;

    public ViewHolderPopular(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public void setIvPoster(ImageView ivPoster) {
        this.ivPoster = ivPoster;
    }
}
