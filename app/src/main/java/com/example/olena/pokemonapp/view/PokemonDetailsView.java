package com.example.olena.pokemonapp.view;


import android.graphics.Bitmap;

public interface PokemonDetailsView extends BaseView {

    void setPokemonImg(Bitmap bitmap);

    void setPokemonName(String name);

    void setPokemonWeight(int weight);

    void setPokemonHeight(int height);


}
