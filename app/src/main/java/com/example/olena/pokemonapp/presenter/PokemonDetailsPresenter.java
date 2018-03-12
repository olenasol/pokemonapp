package com.example.olena.pokemonapp.presenter;


import com.example.olena.pokemonapp.view.ActivityRowView;

public interface PokemonDetailsPresenter extends BasePresenter{

    void onBindActivityRowViewAtPosition(int position, ActivityRowView holder);

    int getActivityRowsCount();

    void loadPokemonData(int id);
}
