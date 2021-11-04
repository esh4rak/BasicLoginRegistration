package com.eshrak.basicloginregistration.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eshrak.basicloginregistration.R;
import com.eshrak.basicloginregistration.adapters.ShowAdapter;
import com.eshrak.basicloginregistration.databinding.ActivityDashboardBinding;
import com.eshrak.basicloginregistration.models.ShowItem;
import com.eshrak.basicloginregistration.viewmodels.DashboardViewModel;
import com.eshrak.basicloginregistration.utils.SharedPreference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private ArrayList<ShowItem> showItemArrayList;
    private ShowAdapter showAdapter;
    private DashboardViewModel dashboardViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        setShowAdapter();
        initViewModel();
        getShows("A");

    }


    private void init() {

        setSupportActionBar(binding.actionBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dashboard");
            getSupportActionBar().show();
        }


        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getShows(newText);
                return false;
            }
        });
    }


    private void initViewModel() {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        dashboardViewModel.init();
    }

    private void setShowAdapter() {

        showItemArrayList = new ArrayList<>();

        binding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        showAdapter = new ShowAdapter(showItemArrayList, getApplicationContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(showAdapter);


        showAdapter.setOnItemClickListener((position, v) -> {
            Intent intent = new Intent(DashboardActivity.this, DetailsActivity.class);
            intent.putExtra("showItemArrayList", showItemArrayList);
            intent.putExtra("position", position);
            startActivity(intent);
        });

    }

    private void getShows(String searchedShow) {
        dashboardViewModel.show(searchedShow);
        dashboardViewModel.getShowLiveData.observe(this, showItems -> {
            if (showItems.size() > 0) {
                showAdapter.getShow(showItems);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanString.length(), 0);
            int end = spanString.length();
            spanString.setSpan(new RelativeSizeSpan(.8f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spanString);

        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {

        if (item.getItemId() == R.id.action_profile) {
            startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            return true;
        } else if (item.getItemId() == R.id.action_logout) {
            SharedPreference sharedPreferences = new SharedPreference(getApplicationContext());
            sharedPreferences.removeUser();

            startActivity(new Intent(DashboardActivity.this, SignInActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}