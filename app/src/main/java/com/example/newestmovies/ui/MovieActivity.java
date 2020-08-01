package com.example.newestmovies.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newestmovies.R;

public class MovieActivity extends AppCompatActivity {
    String BaseUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        String title = getIntent().getStringExtra("title");
        String imagePath = getIntent().getStringExtra("image_path");
        String overView = getIntent().getStringExtra("over_view");

        TextView titleTV = findViewById(R.id.title_moviePage);
        ImageView imageIV = findViewById(R.id.image_moviePage);
        TextView overViewTV = findViewById(R.id.overview_moviePage);

        Glide.with(this)
                .load(Uri.parse(BaseUrl+imagePath))
                .into(imageIV);

        titleTV.setText(title);
        overViewTV.setText(overView);
    }
}