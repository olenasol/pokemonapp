package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class ActivityPokemon {
    @SerializedName(value = "name")
    private String activityName;

    public ActivityPokemon(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
