package com.eshrak.basicloginregistration.api;


import com.eshrak.basicloginregistration.models.Show_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("search/shows")
    Call<List<Show_response>> getShowItems(
            @Query("q") String value
    );

}
