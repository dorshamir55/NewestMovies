package com.example.newestmovies.retrofit;

import com.example.newestmovies.model.MainPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("now_playing")
    Call<MainPojo> getAllMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") String page);
//    https://api.themoviedb.org/3/movie/now_playing?api_key=8e0287bdf8f164cb10683aba2e728f5c&language=en-US&page=1
}