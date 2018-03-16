package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListPageItem {

    @SerializedName(value = "count")
    private int pageCount;

    @SerializedName(value = "results")
    private List<PokemonSimpleItem> listPokemonLinks;

    public List<PokemonSimpleItem> getListPokemonLinks() {
        return listPokemonLinks;
    }

    public int getPageCount() {
        return pageCount;
    }


}
