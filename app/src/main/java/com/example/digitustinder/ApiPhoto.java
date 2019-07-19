package com.example.digitustinder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiPhoto {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("realname")
    @Expose
    public String realname;
    @SerializedName("team")
    @Expose
    public String team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public void setFirstappearance(String firstappearance) {
        this.firstappearance = firstappearance;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @SerializedName("firstappearance")
    @Expose
    public String firstappearance;
    @SerializedName("createdby")
    @Expose
    public String createdby;
    @SerializedName("publisher")
    @Expose
    public String publisher;
    @SerializedName("imageurl")
    @Expose
    public String imageurl;
    @SerializedName("bio")
    @Expose
    public String bio;


}
