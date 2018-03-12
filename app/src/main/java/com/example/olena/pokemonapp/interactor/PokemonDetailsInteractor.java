package com.example.olena.pokemonapp.interactor;


import com.example.olena.pokemonapp.model.PokemonComplexItem;

import java.util.concurrent.ExecutionException;

public interface PokemonDetailsInteractor extends BaseInteractor {
    PokemonComplexItem getPokemonFromDbById(int id) throws ExecutionException, InterruptedException;
}
