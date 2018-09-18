package com.durga.balaji66.ganeshmanagement;

import com.durga.balaji66.ganeshmanagement.Models.Game;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SomeClass {


    @SerializedName("registeredPlayerAnxiety")
    @Expose
    private List<Game> details = null;


    public List<Game> getDetails() {
        return details;
    }

    public void setDetails(List<Game> details) {
        this.details = details;
    }

}
