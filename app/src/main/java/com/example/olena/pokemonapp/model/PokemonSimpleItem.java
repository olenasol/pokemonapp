package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olena on 3/5/2018.
 */

public class PokemonSimpleItem {
    @SerializedName(value = "url")
    private String pokemonDetailsUri;

    public String getPokemonDetailsUri() {
        return pokemonDetailsUri;
    }
}
