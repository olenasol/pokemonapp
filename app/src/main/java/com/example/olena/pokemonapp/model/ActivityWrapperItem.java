package com.example.olena.pokemonapp.model;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class ActivityWrapperItem {
    @SerializedName(value = "ability")
    @Embedded
    private ActivityPokemon activityPokemon;

    public ActivityWrapperItem(ActivityPokemon activityPokemon) {
        this.activityPokemon = activityPokemon;
    }

    public ActivityPokemon getActivityPokemon() {
        return activityPokemon;
    }

    public void setActivityPokemon(ActivityPokemon activityPokemon) {
        this.activityPokemon = activityPokemon;
    }
}
