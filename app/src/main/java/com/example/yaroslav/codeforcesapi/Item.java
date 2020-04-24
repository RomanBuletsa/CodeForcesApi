package com.example.yaroslav.codeforcesapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("handle")
    @Expose
    private String login;
    @SerializedName("titlePhoto")
    @Expose
    private String avatarUrl;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("maxRating")
    @Expose
    private String maxRating;
    @SerializedName("contribution")
    @Expose
    private String contribution;

    public String getLogin(){
        return login;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }

    public String getRating(){
        return rating;
    }

    public String getCountry(){ return country;}

    public String getCity() { return  city;}


    public String getOrganization() { return  organization;}

    public String getMaxRating() { return  maxRating;}

    public String getContribution() { return  contribution;}


}
