package com.example.olena.pokemonapp.presenter;

import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.view.PokemonRowView;

import java.io.IOException;
import java.util.List;

public interface PokemonListPresenter extends BasePresenter{
    void onBindPokemonRowViewAtPosition(int position, PokemonRowView holder);

    int getPokemonRowsCount();

    void processPokemonList(List<PokemonComplexItem> list);

    void getPokemonList();

    void refetchPokemonsFromServer();

}
