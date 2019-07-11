package com.shariqansari.vodoassignment.common;

public interface Constants {
    //Minimum Video you want to buffer while Playing
    int MIN_BUFFER_DURATION = 3000;
    //Max Video you want to buffer during PlayBack
    int MAX_BUFFER_DURATION = 5000;
    //Min Video you want to buffer before start Playing it
    int MIN_PLAYBACK_START_BUFFER = 1500;
    //Min video You want to buffer when user resumes video
    int MIN_PLAYBACK_RESUME_BUFFER = 5000;
}
