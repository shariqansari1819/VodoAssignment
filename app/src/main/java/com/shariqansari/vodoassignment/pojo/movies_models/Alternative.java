
package com.shariqansari.vodoassignment.pojo.movies_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alternative {

    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("url")
    @Expose
    private String url;

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
