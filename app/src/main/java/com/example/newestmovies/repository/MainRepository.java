package com.example.newestmovies.repository;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newestmovies.model.MainPojo;
import com.example.newestmovies.model.Movies;
import com.example.newestmovies.retrofit.APIClient;
import com.example.newestmovies.retrofit.APIInterface;
import com.example.newestmovies.viewmodel.IMainViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.logging.LogRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository implements IMainRepository{
    MutableLiveData<List<Movies>> movies = new MutableLiveData<>();
    private Context context;

    public MainRepository(Application application) {
        this.context = application.getApplicationContext();
    }

    @Override
    public void getMoviesLiveData(String apiKey, String language, String page, Runnable onFinish) {
        APIInterface apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<MainPojo> call = apiInterface.getAllMovies(apiKey, language,page);

        call.enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, retrofit2.Response<MainPojo> response) {
                movies.setValue(Arrays.asList(response.body().getMovies()));
            }

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                movies.setValue(null);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(onFinish != null) {
                    onFinish.run();
                }
            }
        }, 1000);
    }

    @Override
    public LiveData<List<Movies>> getAllMovies() {
        return movies;
    }
}
