package com.example.newestmovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newestmovies.model.Movies;
import com.example.newestmovies.repository.IMainRepository;
import com.example.newestmovies.repository.MainRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel implements IMainViewModel {
    private IMainRepository mainRepository;
    private LiveData<List<Movies>> moviesLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.mainRepository = new MainRepository(application);
    }

    @Override
    public void getMoviesLiveData(String apiKey, String language, String page, Runnable onFinish) {
        mainRepository.getMoviesLiveData(apiKey, language, page, onFinish);
    }

    @Override
    public LiveData<List<Movies>> getAllMovies() {
        return mainRepository.getAllMovies();
    }
}
