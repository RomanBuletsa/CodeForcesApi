package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingChangeList {
    @SerializedName("result")
    @Expose
    private List<RatingChange> RatingChange;

    public List<RatingChange> getRatingChange(){
        return RatingChange;
    }
}
