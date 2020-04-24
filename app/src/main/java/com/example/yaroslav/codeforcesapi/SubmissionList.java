package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubmissionList {
    @SerializedName("result")
    @Expose
    private List<Submission> Submission;

    public List<Submission> getSubmission(){
        return Submission;
    }
}
