package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class AbilityPokemon {
    @SerializedName(value = "name")
    private String abilityName;

    public AbilityPokemon(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
