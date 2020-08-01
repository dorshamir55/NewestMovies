package com.example.newestmovies.adapter;

import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newestmovies.R;
import com.example.newestmovies.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movies> moviesList;
    private MyMoviesListener listener;
    final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/";

    public MoviesAdapter() {

    }

    public void setData(List<Movies> data) {
        moviesList = data;
    }

    public interface MyMoviesListener {
        void onMovieClicked(String title, String imagePath, String overView, View view);
    }

    public void setListener(MyMoviesListener listener) {
        this.listener=listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTv;
        TextView overViewTv;
        ImageView imageIv;
        ViewHolder(View itemView){
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_cell);
            overViewTv = itemView.findViewById(R.id.overview_cell);
            imageIv = itemView.findViewById(R.id.image_cell);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        Log.d("tag", "holder.itemView clicked");
                        assert moviesList != null;
                        Movies movie = moviesList.get(getAdapterPosition());
                        listener.onMovieClicked(movie.getTitle(), movie.getPoster_path(), movie.getOverView(), v);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        assert moviesList != null;
        Movies movie = moviesList.get(position);
        holder.titleTv.setText(movie.getTitle());
        Picasso.get().load(BASE_URL_IMAGE+movie.getPoster_path()).into(holder.imageIv);
        holder.overViewTv.setText(movie.getOverView());

    }

    @Override
    public int getItemCount() {
        if (moviesList == null){
            return 0;
        }
        return moviesList.size();
    }
}
