package com.reactlibrary;

public class Movie {
    private String title;
    private String imageUrl;
    private String MovieId;

    public Movie(String title, String imageUrl, String MovieId){
        this.title = title;
        this.imageUrl = imageUrl;
        this.MovieId = MovieId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getMovieId() {
        return MovieId;
    }
}

