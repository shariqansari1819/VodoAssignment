package com.shariqansari.vodoassignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.shariqansari.vodoassignment.R;
import com.shariqansari.vodoassignment.adapters.MoviesGenreAdapter;
import com.shariqansari.vodoassignment.common.Constants;
import com.shariqansari.vodoassignment.endpoints.EndpointKeys;
import com.shariqansari.vodoassignment.pojo.movies_models.MoviesMainObject;
import com.shariqansari.vodoassignment.utils.ValidUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements Player.EventListener {

    //    Instance fields....
    MoviesMainObject moviesMainObject;
    String imagePath, videoUrl;
    Handler mHandler;
    Runnable mRunnable;

    //    Android fields....
    @BindView(R.id.toolbarMovieDetail)
    Toolbar toolbarMovieDetail;
    @BindView(R.id.exoPlayerMovieDetail)
    PlayerView exoPlayer;
    @BindView(R.id.imageViewThumbnailMovieDetail)
    ImageView imageViewThumbnail;
    @BindView(R.id.textViewTitleMovieDetail)
    TextView textViewTitle;
    @BindView(R.id.textViewYearMovieDetail)
    TextView textViewYear;
    @BindView(R.id.textViewDurationMovieDetail)
    TextView textViewDuration;
    private SimpleExoPlayer simpleExoPlayer;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewSynopsisHeader)
    TextView textViewSynopsis;
    @BindView(R.id.textViewOverviewMoviesDetail)
    TextView textViewOverview;
    @BindView(R.id.textViewGenreHeader)
    TextView textViewGenreHeader;
    @BindView(R.id.recyclerViewGenreMoviesDetail)
    RecyclerView recyclerViewGenre;
    @BindView(R.id.textViewRatingMovieDetail)
    TextView textViewRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        ValidUtils.transparentStatusAndNavigation(this);

//        Setting custom action bar....
        setSupportActionBar(toolbarMovieDetail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.movie_detail));
        }

//        Getting movie object from previous intent object....
        if (getIntent() != null) {
            moviesMainObject = getIntent().getParcelableExtra(EndpointKeys.MOVIE_OBJECT);
            imagePath = getIntent().getStringExtra(EndpointKeys.POSTER_PATH);
            videoUrl = getIntent().getStringExtra(EndpointKeys.VIDEO_URL);
            if (moviesMainObject != null) {
                setMovieDetailData(moviesMainObject);
            }
        }

    }

    //    TODO: Method to set movie data....
    private void setMovieDetailData(MoviesMainObject movieDetailData) {
        if (movieDetailData.getHeadline() != null) {
            textViewTitle.setText(movieDetailData.getHeadline());
        }
        if (imagePath != null) {
            Glide.with(this)
                    .load(imagePath)
                    .apply(new RequestOptions().centerCrop())
                    .apply(new RequestOptions().placeholder(R.drawable.image_placeholder))
                    .apply(new RequestOptions().error(R.drawable.image_placeholder))
                    .thumbnail(0.1f)
                    .into(imageViewThumbnail);
        }
        if (movieDetailData.getYear() != null) {
            textViewYear.setText(movieDetailData.getYear());
        }
        if (movieDetailData.getDuration() != null) {
            int[] time = ValidUtils.splitToComponentTimes(movieDetailData.getDuration());
            textViewDuration.setText(time[0] + "h " + time[1] + "m " + time[2] + "s");
        }
        if (movieDetailData.getSynopsis() != null) {
            textViewOverview.setText(movieDetailData.getSynopsis());
        }
        if (movieDetailData.getRating() != null) {
            textViewRating.setText(String.valueOf(movieDetailData.getRating()));
        }
        if (videoUrl != null) {
            setUp(videoUrl);
        }
        if (movieDetailData.getGenres().size() > 0) {
            textViewGenreHeader.setVisibility(View.VISIBLE);
            recyclerViewGenre.setVisibility(View.VISIBLE);
            recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            recyclerViewGenre.setAdapter(new MoviesGenreAdapter(this, movieDetailData.getGenres()));
        } else {
            textViewGenreHeader.setVisibility(View.GONE);
            recyclerViewGenre.setVisibility(View.GONE);
        }
    }

    private void initializePlayer() {
        if (simpleExoPlayer == null) {
            // 1. Create a default TrackSelector
            LoadControl loadControl = new DefaultLoadControl(
                    new DefaultAllocator(true, 16),
                    Constants.MIN_BUFFER_DURATION,
                    Constants.MAX_BUFFER_DURATION,
                    Constants.MIN_PLAYBACK_START_BUFFER,
                    Constants.MIN_PLAYBACK_RESUME_BUFFER, -1, true);

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            // 2. Create the player
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this), trackSelector, loadControl);
            exoPlayer.setPlayer(simpleExoPlayer);
        }
    }

    private void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mUri);
        // Prepare the player with the source.
        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(this);
    }

    private void setUp(String url) {
        initializePlayer();
        buildMediaSource(Uri.parse(url));
    }

    private void releasePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    private void pausePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.getPlaybackState();
        }
    }

    private void resumePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(true);
            simpleExoPlayer.getPlaybackState();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausePlayer();
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {
            case Player.STATE_BUFFERING:
                progressBar.setVisibility(View.VISIBLE);
                break;
            case Player.STATE_ENDED:
                break;
            case Player.STATE_IDLE:
                break;
            case Player.STATE_READY:
                progressBar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}