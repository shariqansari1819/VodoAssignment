package com.shariqansari.vodoassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shariqansari.vodoassignment.R;
import com.shariqansari.vodoassignment.pojo.event_bus.EventBusMovieClick;
import com.shariqansari.vodoassignment.pojo.movies_models.MoviesMainObject;
import com.shariqansari.vodoassignment.utils.ValidUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    //    Android fields....
    private Context context;
    private LayoutInflater layoutInflater;

    //    Instance fields....
    private List<MoviesMainObject> moviesResultArrayList = new ArrayList<>();

    public MoviesAdapter(Context context, List<MoviesMainObject> moviesResultArrayList) {
        this.context = context;
        this.moviesResultArrayList = moviesResultArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_movies, parent, false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        MoviesMainObject moviesMainObject = moviesResultArrayList.get(position);
        if (moviesMainObject != null) {
            String headline = moviesMainObject.getHeadline();
            String year = moviesMainObject.getYear();
            int rating = 0, duration = 0;
            if (moviesMainObject.getRating() != null)
                rating = moviesMainObject.getRating();
            String imagePath = "";
            if (moviesMainObject.getCardImages() != null && moviesMainObject.getCardImages().size() > 0) {
                imagePath = moviesMainObject.getCardImages().get(0).getUrl();
            }
            if (moviesMainObject.getHeadline() != null) {
                holder.textViewMovieTitle.setText(headline);
            }
            if (year != null) {
                holder.textViewMovieYear.setText(year);
            }
            if (moviesMainObject.getDuration() != null) {
                duration = moviesMainObject.getDuration();
            }
            holder.textViewRatingCount.setText(String.valueOf(rating));
            int[] time = ValidUtils.splitToComponentTimes(duration);
            holder.textViewDurationTime.setText(time[0] + "h " + time[1] + "m " + time[2] + "s");
            Glide.with(context)
                    .load(imagePath)
                    .apply(new RequestOptions().centerCrop())
                    .apply(new RequestOptions().placeholder(R.drawable.zootopia_thumbnail))
                    .apply(new RequestOptions().error(R.drawable.zootopia_thumbnail))
                    .into(holder.imageViewThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return moviesResultArrayList.size();
    }

    class MoviesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageViewThumbnailMoviesRow)
        ImageView imageViewThumbnail;
        @BindView(R.id.textViewTitleMoviesRow)
        TextView textViewMovieTitle;
        @BindView(R.id.textViewYearMoviesRow)
        TextView textViewMovieYear;
        @BindView(R.id.textViewAudienceMainRow)
        TextView textViewRatingText;
        @BindView(R.id.textViewRatingMoviesRow)
        TextView textViewRatingCount;
        @BindView(R.id.textViewDurationTimeMain)
        TextView textViewDurationTime;

        MoviesHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new EventBusMovieClick(getAdapterPosition()));
        }

    }

}
