
package com.shariqansari.vodoassignment.pojo.movies_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeyArtImage {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("h")
    @Expose
    private Integer h;
    @SerializedName("w")
    @Expose
    private Integer w;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

}
