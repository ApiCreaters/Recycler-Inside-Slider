package com.example.retrofitandrecycler.Actvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofitandrecycler.Adapter.ImageAdapter;
import com.example.retrofitandrecycler.ApiClient.RetrofitClient;
import com.example.retrofitandrecycler.Interface.AdapterClick;
import com.example.retrofitandrecycler.Model.Hit;
import com.example.retrofitandrecycler.Model.Respons;
import com.example.retrofitandrecycler.R;
import com.example.retrofitandrecycler.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterClick {

    private ActivityMainBinding binding;
    private List<Hit> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageList = new ArrayList<>();

        binding.rvGallery.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        binding.rvGallery.setLayoutManager(gridLayoutManager);

        getThumbhanii();
    }

    private void getThumbhanii() {
        Call<Respons> responsCall = RetrofitClient.getInstance().getMyApi().getImages(1, 50);
        responsCall.enqueue(new Callback<Respons>() {
            @Override
            public void onResponse(Call<Respons> call, Response<Respons> response) {
                if (response.isSuccessful()) {
                    Respons myResponse = response.body();
                    if (myResponse != null) {
                        imageList = myResponse.getHits();
                        if (imageList != null && imageList.size() > 0) {
                            ImageAdapter adapter = new ImageAdapter(MainActivity.this, imageList, MainActivity.this, 0);
                            binding.rvGallery.setAdapter(adapter);
                        }
                    }
                    binding.pbGallery.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Respons> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                binding.pbGallery.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void ItemClick(Hit hits, int Position) {
        Intent intent = new Intent(MainActivity.this, FullScreenImageActivity.class);
        intent.putExtra("position", Position);
        startActivity(intent);

    }
}