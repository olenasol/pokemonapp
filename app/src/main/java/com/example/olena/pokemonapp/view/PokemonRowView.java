package com.example.olena.pokemonapp.view;

import android.content.Context;
import android.view.View;

import com.example.olena.pokemonapp.model.PokemonComplexItem;

public interface PokemonRowView {
    void setPokemonNameText(String pokemonName);

    void setPokemonImg(PokemonComplexItem complexItem, Context context);

    void setPokemonItemOnClick(View.OnClickListener onClick);
}
