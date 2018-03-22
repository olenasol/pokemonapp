package com.example.olena.pokemonapp.view;


import android.graphics.Bitmap;

import com.example.olena.pokemonapp.model.PokemonComplexItem;

public interface PokemonDetailsView extends BaseView {

    void setPokemonUI(PokemonComplexItem pokemonItem);

    void setPokemonImg(Bitmap bitmap);

}
