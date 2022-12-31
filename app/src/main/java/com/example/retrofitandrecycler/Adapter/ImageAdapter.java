package com.example.retrofitandrecycler.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitandrecycler.Interface.AdapterClick;
import com.example.retrofitandrecycler.Model.Hit;
import com.example.retrofitandrecycler.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<Hit> imgLIst;
    private final AdapterClick adapterClick;
    private final int adapterType;

    public ImageAdapter(Context context, List<Hit> imgLIst, AdapterClick adapterClick, int adapterType) {
        this.context = context;
        this.imgLIst = imgLIst;
        this.adapterClick = adapterClick;
        this.adapterType = adapterType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (adapterType == 0) {
            return new ImageThumbnailViewHolder(LayoutInflater.from(context).inflate(R.layout.image_row_layout, parent, false));
        } else {
            return new ImageFullScreenViewHolder(LayoutInflater.from(context).inflate(R.layout.image_full_screen_row_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (adapterType == 0) {
            ((ImageThumbnailViewHolder) holder).bidData(imgLIst.get(position));

        } else {
            ((ImageFullScreenViewHolder) holder).bidData(imgLIst.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return imgLIst.size();
    }


    public class ImageThumbnailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivThumbnail;

        public ImageThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            ivThumbnail.setOnClickListener(this);
        }

        public void bidData(Hit hit) {
            Glide.with(context).load(hit.getLargeImageURL()).into(ivThumbnail);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            adapterClick.ItemClick(imgLIst.get(position), position);
        }
    }

    public class ImageFullScreenViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFullScreenImage;

        public ImageFullScreenViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFullScreenImage = itemView.findViewById(R.id.ivFullScreen);
        }

        public void bidData(Hit hit) {
            Glide.with(context).load(hit.getLargeImageURL()).into(ivFullScreenImage);
        }
    }
}


//https://pixabay.com/api/?key=8003210-6df7aad0c179184ba4b447bc2