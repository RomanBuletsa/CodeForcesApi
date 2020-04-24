package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("result")
    @Expose
    private List<Item> items;

    public List<Item> getItems(){
        return items;
    }

}

