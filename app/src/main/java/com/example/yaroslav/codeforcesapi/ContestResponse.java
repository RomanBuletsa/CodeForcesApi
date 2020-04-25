package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContestResponse {
    @SerializedName("result")
    @Expose
    private List<Contest> contests;

    public List<Contest> getContests(){
        return contests;
    }

}
