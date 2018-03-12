package com.example.olena.pokemonapp.view;


public interface PokemonListView extends BaseView {
    void notifyAdapterSetChanged();

    void setProgressbarVisible();

    void setRecyclerViewVisible();
}
