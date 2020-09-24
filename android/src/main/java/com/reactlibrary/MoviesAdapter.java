package com.reactlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> movieList;
    private static ClickListener clickListener;
    ReactContext context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageView;
        TextView myTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.moviePoster);
            myTextView = itemView.findViewById(R.id.movieTitleText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public MoviesAdapter(List<Movie> data, ThemedReactContext context){
        this.movieList = data;
        this.context = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MoviesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        ImageView image = holder.myImageView;
        new DownloadImageTask(image).execute(movie.getImageUrl());
        TextView movieTitle = holder.myTextView;
        movieTitle.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
