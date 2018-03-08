package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olena on 3/5/2018.
 */

public class SpritePokemon {
    @SerializedName(value="front_default")
    private String frontDefault;

    public SpritePokemon(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

}
