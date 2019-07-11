
package com.shariqansari.vodoassignment.pojo.movies_models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesMainObject {

    @SerializedName("skyGoUrl")
    @Expose
    private String skyGoUrl;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("reviewAuthor")
    @Expose
    private String reviewAuthor;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cert")
    @Expose
    private String cert;
    @SerializedName("viewingWindow")
    @Expose
    private ViewingWindow viewingWindow;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("cardImages")
    @Expose
    private List<CardImage> cardImages = null;
    @SerializedName("directors")
    @Expose
    private List<Director> directors = null;
    @SerializedName("sum")
    @Expose
    private String sum;
    @SerializedName("keyArtImages")
    @Expose
    private List<KeyArtImage> keyArtImages = null;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("skyGoId")
    @Expose
    private String skyGoId;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("quote")
    @Expose
    private String quote;

    public String getSkyGoUrl() {
        return skyGoUrl;
    }

    public void setSkyGoUrl(String skyGoUrl) {
        this.skyGoUrl = skyGoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public ViewingWindow getViewingWindow() {
        return viewingWindow;
    }

    public void setViewingWindow(ViewingWindow viewingWindow) {
        this.viewingWindow = viewingWindow;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public List<CardImage> getCardImages() {
        return cardImages;
    }

    public void setCardImages(List<CardImage> cardImages) {
        this.cardImages = cardImages;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<KeyArtImage> getKeyArtImages() {
        return keyArtImages;
    }

    public void setKeyArtImages(List<KeyArtImage> keyArtImages) {
        this.keyArtImages = keyArtImages;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public String getSkyGoId() {
        return skyGoId;
    }

    public void setSkyGoId(String skyGoId) {
        this.skyGoId = skyGoId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

}
