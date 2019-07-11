package com.shariqansari.vodoassignment.api;

import com.shariqansari.vodoassignment.pojo.movies_models.MoviesMainObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("vodassets/showcase.json")
    Call<List<MoviesMainObject>> getAllData();

}