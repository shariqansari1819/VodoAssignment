package com.shariqansari.vodoassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shariqansari.vodoassignment.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesGenreAdapter extends RecyclerView.Adapter<MoviesGenreAdapter.MoviesGenreHolder> {

    private List<String> genreList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MoviesGenreAdapter(Context context, List<String> genreList) {
        layoutInflater = LayoutInflater.from(context);
        this.genreList = genreList;
    }

    @NonNull
    @Override
    public MoviesGenreAdapter.MoviesGenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesGenreAdapter.MoviesGenreHolder(layoutInflater.inflate(R.layout.row_genre_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesGenreAdapter.MoviesGenreHolder genreHolder, int position) {
        genreHolder.textViewGenre.setText(genreList.get(position));
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }


    class MoviesGenreHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewGenreMoviesRow)
        TextView textViewGenre;

        MoviesGenreHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
