package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submission {
    @SerializedName("problem")
    @Expose
    private Problem problem;

    @SerializedName("programmingLanguage")
    @Expose
    private String programmingLanguage;

    @SerializedName("verdict")
    @Expose
    private String verdict;


    public Problem getProblem(){ return problem; }
    public String getProgrammingLanguage(){
        return programmingLanguage;
    }
    public String getVerdict(){ return verdict; }

}
