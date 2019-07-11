
package com.shariqansari.vodoassignment.pojo.movies_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewingWindow {

    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("wayToWatch")
    @Expose
    private String wayToWatch;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("title")
    @Expose
    private String title;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getWayToWatch() {
        return wayToWatch;
    }

    public void setWayToWatch(String wayToWatch) {
        this.wayToWatch = wayToWatch;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
