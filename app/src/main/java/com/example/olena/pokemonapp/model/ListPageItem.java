package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olena on 3/5/2018.
 */

public class ListPageItem {

    @SerializedName(value = "results")
    private List<PokemonSimpleItem> listPokemonLinks;

    public List<PokemonSimpleItem> getListPokemonLinks() {
        return listPokemonLinks;
    }
}
