package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contest {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("durationSeconds")
    @Expose
    private String duration;
    @SerializedName("startTimeSeconds")
    @Expose
    private String startTime;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDuration(){
        return duration;
    }

    public String getStartTime(){
        return startTime;
    }
}
