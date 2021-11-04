package com.eshrak.basicloginregistration.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eshrak.basicloginregistration.api.ApiService;
import com.eshrak.basicloginregistration.api.RetrofitService;
import com.eshrak.basicloginregistration.models.ShowItem;
import com.eshrak.basicloginregistration.models.Show_response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardRepository {

    private final ApiService apiService;

    public DashboardRepository() {
        apiService = RetrofitService.cteateService(ApiService.class);
    }

    private static DashboardRepository dashboardRepository;

    public static DashboardRepository getInstance() {
        if (dashboardRepository == null) {
            dashboardRepository = new DashboardRepository();
        }
        return dashboardRepository;
    }


    //get shows
    public LiveData<ArrayList<ShowItem>> getShows(String query) {
        final MutableLiveData<ArrayList<ShowItem>> data = new MutableLiveData<>();

        final ArrayList<ShowItem> items = new ArrayList<>();

        apiService.getShowItems(query).enqueue(new Callback<List<Show_response>>() {
            @Override
            public void onResponse(@NonNull Call<List<Show_response>> call, @NonNull Response<List<Show_response>> response) {
                if (response.isSuccessful()) {

                    if (response.body().size() > 0) {

                        for (int i = 0; i < response.body().size(); i++) {
                            Show_response data = response.body().get(i);

                            items.add(
                                    new ShowItem(
                                            data.getShow().getId(),
                                            data.getShow().getName(),
                                            data.getShow().getImage() != null ? data.getShow().getImage().getOriginal() : "",
                                            data.getShow().getLanguage(),
                                            data.getShow().getType(),
                                            data.getShow().getPremiered(),
                                            data.getShow().getRating().getAverage(),
                                            data.getShow().getSummary()
                                    )
                            );
                        }
                        data.setValue(items);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Show_response>> call, Throwable t) {

            }
        });
        return data;
    }


}
