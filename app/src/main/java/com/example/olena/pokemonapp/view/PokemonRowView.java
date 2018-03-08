package com.example.olena.pokemonapp.view;

import android.content.Context;

/**
 * Created by olena on 3/5/2018.
 */

public interface PokemonRowView {
    void setPokemonNameText(String pokemonName);
    void setPokemonHeightText(String pokemonHeight);
    void setPokemonWeightText(String pokemonWeight);
    void setPokemonImg(String pokemonImgUrl, Context context);
}
