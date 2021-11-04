package com.eshrak.basicloginregistration.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.eshrak.basicloginregistration.R;
import com.eshrak.basicloginregistration.databinding.ActivityDetailsBinding;
import com.eshrak.basicloginregistration.models.ShowItem;

import org.jsoup.Jsoup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();

    }


    private void init() {
        setSupportActionBar(binding.actionBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24);
            getSupportActionBar().setTitle("Details");
            getSupportActionBar().show();
        }


        ArrayList<ShowItem> showItemArrayList = (ArrayList<ShowItem>) getIntent().getSerializableExtra("showItemArrayList");
        int position = getIntent().getIntExtra("position", 0);


        URL url = null;
        try {
            url = new URL(showItemArrayList.get(position).getShowImage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        Glide.with(getApplicationContext())
                .load(url)
                .error(R.drawable.ic_image_not_found)
                .centerCrop()
                .into(binding.showImage);

        if (showItemArrayList.get(position).getShowName() != null && !showItemArrayList.get(position).getShowName().isEmpty()) {
            binding.nameTV.setText(showItemArrayList.get(position).getShowName());
        } else {
            binding.nameTV.setText("N/A");
        }


        if (showItemArrayList.get(position).getType() != null && !showItemArrayList.get(position).getType().isEmpty()) {
            binding.typeTV.setText(showItemArrayList.get(position).getType());
        } else {
            binding.typeTV.setText("N/A");
        }


        if (showItemArrayList.get(position).getLanguage() != null && !showItemArrayList.get(position).getLanguage().isEmpty()) {
            binding.languageTV.setText(showItemArrayList.get(position).getLanguage());
        } else {
            binding.languageTV.setText("N/A");
        }


        if (showItemArrayList.get(position).getRating() != null && !showItemArrayList.get(position).getRating().isEmpty()) {
            binding.ratingTV.setText(showItemArrayList.get(position).getRating());
        } else {
            binding.ratingTV.setText("N/A");
        }


        if (showItemArrayList.get(position).getPremiered() != null && !showItemArrayList.get(position).getPremiered().isEmpty()) {
            binding.premieredTV.setText(showItemArrayList.get(position).getPremiered());
        } else {
            binding.premieredTV.setText("N/A");
        }


        if (showItemArrayList.get(position).getSummary() != null && !showItemArrayList.get(position).getSummary().isEmpty()) {
            binding.summaryTV.setText(Jsoup.parse(showItemArrayList.get(position).getSummary()).text());
        } else {
            binding.summaryTV.setText("N/A");
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
}