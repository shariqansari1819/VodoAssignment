package com.shariqansari.vodoassignment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.shariqansari.vodoassignment.R;
import com.shariqansari.vodoassignment.endpoints.EndpointKeys;
import com.shariqansari.vodoassignment.pojo.movies_models.MoviesMainObject;

public class MovieDetailActivity extends AppCompatActivity {

    //    Instance fields....
    MoviesMainObject moviesMainObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

//        Getting movie object from previous intent object....
        if (getIntent() != null) {
            moviesMainObject = getIntent().getParcelableExtra(EndpointKeys.MOVIE_OBJECT);
            
        }

    }

}