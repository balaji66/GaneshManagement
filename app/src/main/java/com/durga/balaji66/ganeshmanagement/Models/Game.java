package com.durga.balaji66.ganeshmanagement.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Game {

    private String candidate_name;
    private String father_name;
    private String mobile_number;

    public String getCandidate_name() {
        return candidate_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }
}
