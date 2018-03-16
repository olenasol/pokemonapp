package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class PokemonSimpleItem {

    @SerializedName(value = "url")
    private String pokemonDetailsUri;

    public String getPokemonDetailsUri() {
        return pokemonDetailsUri;
    }

    public void setPokemonDetailsUri(String pokemonDetailsUri) {
        this.pokemonDetailsUri = pokemonDetailsUri;
    }
}
