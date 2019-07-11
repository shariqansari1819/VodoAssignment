package com.shariqansari.vodoassignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shariqansari.vodoassignment.R;
import com.shariqansari.vodoassignment.adapters.MoviesAdapter;
import com.shariqansari.vodoassignment.api.Api;
import com.shariqansari.vodoassignment.api.ApiClient;
import com.shariqansari.vodoassignment.pojo.event_bus.EventBusMovieClick;
import com.shariqansari.vodoassignment.pojo.movies_models.MoviesMainObject;
import com.shariqansari.vodoassignment.utils.ValidUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    //    Android fields....
    @BindView(R.id.appBarMain)
    Toolbar toolbarMain;
    @BindView(R.id.recyclerViewMoviesMain)
    RecyclerView recyclerViewMain;
    @BindView(R.id.linearLayoutRetryMain)
    LinearLayout linearLayoutRetry;
    @BindView(R.id.imageViewErrorMain)
    AppCompatImageView imageViewError;
    @BindView(R.id.textViewErrorMessageMain)
    TextView textViewError;
    @BindView(R.id.textViewRetryMessageMain)
    TextView textViewRetry;

    //    Resource fields....
    @BindString(R.string.could_not_get_movies)
    String couldNotGetMovies;
    @BindString(R.string.internet_problem)
    String internetProblem;

    //    Retrofit fields....
    private Call<List<MoviesMainObject>> topRatedMoviesCall;

    //    Adapter fields....
    private List<MoviesMainObject> moviesMainObjectList = new ArrayList<>();
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        EventBus.getDefault().register(this);

        //        Setting custom action bar....
        setSupportActionBar(toolbarMain);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

//        Setting recyclerview configurations....
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
        if (recyclerViewMain.getItemAnimator() != null)
            recyclerViewMain.getItemAnimator().setAddDuration(500);
        moviesAdapter = new MoviesAdapter(this, moviesMainObjectList);
        recyclerViewMain.setAdapter(moviesAdapter);

        if (ValidUtils.isNetworkAvailable(this)) {
            getMoviesList();
        }
    }

    private void getMoviesList() {
        topRatedMoviesCall = ApiClient.getClient().create(Api.class).getAllData();
        topRatedMoviesCall.enqueue(new Callback<List<MoviesMainObject>>() {
            @Override
            public void onResponse(Call<List<MoviesMainObject>> call, retrofit2.Response<List<MoviesMainObject>> response) {
//                circularProgressBar.setVisibility(View.GONE);
                textViewError.setVisibility(View.GONE);
                imageViewError.setVisibility(View.GONE);
                textViewRetry.setVisibility(View.GONE);
                if (response != null && response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        moviesMainObjectList.add(response.body().get(i));
                        moviesAdapter.notifyItemInserted(i);
                    }
                } else {
                    textViewRetry.setVisibility(View.VISIBLE);
                    textViewError.setVisibility(View.VISIBLE);
                    imageViewError.setVisibility(View.VISIBLE);
                    textViewError.setText(couldNotGetMovies);
                }
            }

            @Override
            public void onFailure(Call<List<MoviesMainObject>> call, Throwable error) {
                if (call.isCanceled() || "Canceled".equals(error.getMessage())) {
                    return;
                }
//                circularProgressBar.setVisibility(View.GONE);
                textViewRetry.setVisibility(View.VISIBLE);
                textViewError.setVisibility(View.VISIBLE);
                imageViewError.setVisibility(View.VISIBLE);

                if (error != null) {
                    if (error.getMessage().contains("No address associated with hostname")) {
                        textViewError.setText(internetProblem);
                    } else {
                        textViewError.setText(couldNotGetMovies);
                    }
                } else {
                    textViewError.setText(couldNotGetMovies);
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusMovieClick(EventBusMovieClick eventBusMovieClick) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (topRatedMoviesCall != null && topRatedMoviesCall.isExecuted()) {
            topRatedMoviesCall.cancel();
        }
    }
}