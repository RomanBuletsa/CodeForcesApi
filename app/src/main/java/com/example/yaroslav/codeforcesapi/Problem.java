package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Problem {

    @SerializedName("index")
    @Expose
    private String index;

    @SerializedName("tags")
    @Expose
    private List<String>  tags;

    public String getIndex(){
        return index;
    }
    public List<String> getTags(){ return tags; }

}
