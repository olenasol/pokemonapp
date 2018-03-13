package com.example.olena.pokemonapp.model;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class AbilityWrapperItem {
    @SerializedName(value = "ability")
    @Embedded
    private AbilityPokemon abilityPokemon;

    public AbilityWrapperItem(AbilityPokemon abilityPokemon) {
        this.abilityPokemon = abilityPokemon;
    }

    public AbilityPokemon getAbilityPokemon() {
        return abilityPokemon;
    }

    public void setAbilityPokemon(AbilityPokemon abilityPokemon) {
        this.abilityPokemon = abilityPokemon;
    }
}
