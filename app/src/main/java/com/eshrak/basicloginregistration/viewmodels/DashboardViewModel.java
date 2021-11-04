package com.eshrak.basicloginregistration.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.eshrak.basicloginregistration.models.ShowItem;
import com.eshrak.basicloginregistration.repositories.DashboardRepository;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {

    private DashboardRepository dashboardRepository;


    public LiveData<ArrayList<ShowItem>> getShowLiveData;

    public String query;

    public void init() {
        if (getShowLiveData != null) {
            return;
        }
        dashboardRepository = DashboardRepository.getInstance();
    }


    public void show(String value) {
        query = value;
        getShowLiveData = dashboardRepository.getShows(query);
    }


}
