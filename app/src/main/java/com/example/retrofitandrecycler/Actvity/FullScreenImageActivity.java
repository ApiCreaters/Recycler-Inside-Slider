package com.example.retrofitandrecycler.Actvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitandrecycler.Adapter.ImageAdapter;
import com.example.retrofitandrecycler.ApiClient.RetrofitClient;
import com.example.retrofitandrecycler.Model.Hit;
import com.example.retrofitandrecycler.Model.Respons;
import com.example.retrofitandrecycler.R;
import com.example.retrofitandrecycler.databinding.ActivityFullScreenImageBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullScreenImageActivity extends AppCompatActivity {

    private ActivityFullScreenImageBinding binding;
    private List<Hit> imageList;
    private int positionss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullScreenImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            positionss = intent.getIntExtra("position", 0);
        }

        binding.rvImageFullScreen.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvImageFullScreen.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvImageFullScreen);

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
                            ImageAdapter adapter = new ImageAdapter(FullScreenImageActivity.this, imageList, null, 1);
                            binding.rvImageFullScreen.setAdapter(adapter);
                            binding.rvImageFullScreen.scrollToPosition(positionss);
                        }
                    }
                    binding.pbImageFullScreen.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Respons> call, Throwable t) {
                Toast.makeText(FullScreenImageActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                binding.pbImageFullScreen.setVisibility(View.GONE);
            }
        });
    }

}