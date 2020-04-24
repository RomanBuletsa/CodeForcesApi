package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingChange {
    @SerializedName("oldRating")
    @Expose
    private String oldRating;

    @SerializedName("newRating")
    @Expose
    private String newRating;

    public String getOldRating(){ return oldRating; }
    public String getNewRating(){ return newRating; }

}
