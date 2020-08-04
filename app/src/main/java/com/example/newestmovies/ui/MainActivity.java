package com.example.newestmovies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newestmovies.R;
import com.example.newestmovies.adapter.MoviesAdapter;
import com.example.newestmovies.model.Movies;
import com.example.newestmovies.viewmodel.IMainViewModel;
import com.example.newestmovies.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private IMainViewModel viewModel;
    MoviesAdapter moviesAdapter = new MoviesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeContainer);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(moviesAdapter);

        moviesAdapter.setListener(new MoviesAdapter.MyMoviesListener() {
            @Override
            public void onMovieClicked(String title, String imagePath, String overView, View view) {
                Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("image_path", imagePath);
                intent.putExtra("over_view", overView);
                startActivity(intent);
            }

        });

        viewModel.getAllMovies().observe(this, movies -> {
                moviesAdapter.setData(movies);
                moviesAdapter.notifyDataSetChanged();
        });
        viewModel.getMoviesLiveData("8e0287bdf8f164cb10683aba2e728f5c", "en-US", "1", null);

        swipeRefreshLayout.setOnRefreshListener(()->{
            //onRefresh
            viewModel.getMoviesLiveData("8e0287bdf8f164cb10683aba2e728f5c", "en-US", "1", ()->{
                swipeRefreshLayout.setRefreshing(false);
            });
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
}