package com.example.olena.pokemonapp.presenter;

import android.content.Context;

import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.ui.PokemonViewHolder;
import com.example.olena.pokemonapp.view.PokemonRowView;

import java.util.List;

/**
 * Created by olena on 3/5/2018.
 */

public interface PokemonListPresenter {
    void onBindPokemonRowViewAtPosition(int position, PokemonRowView holder, Context context);

    int getPokemonRowsCount();

    void fillInPokemonList(List<PokemonComplexItem> list);
}
