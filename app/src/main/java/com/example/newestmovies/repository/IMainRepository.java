package com.example.newestmovies.repository;

import androidx.lifecycle.LiveData;

import com.example.newestmovies.model.Movies;

import java.util.List;

public interface IMainRepository {
    public void getMoviesLiveData(String apiKey, String language, String page, Runnable onFinish);
    public LiveData<List<Movies>> getAllMovies();
}
