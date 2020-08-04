package com.example.newestmovies.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.newestmovies.model.Movies;

import java.util.List;

public interface IMainViewModel {
    public void getMoviesLiveData(String apiKey, String language, String page, Runnable onFinish);
    public LiveData<List<Movies>> getAllMovies();
}
