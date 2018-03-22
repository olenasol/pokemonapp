package com.example.olena.pokemonapp.interactor;

import com.example.olena.pokemonapp.model.PokemonComplexItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PokemonListInteractor extends BaseInteractor {

    void retrieveListOfComplexPokemons(int pageNumber);

    void fillPokemonDb(List<PokemonComplexItem> list);

    void getPokemonsFromDb(int pageNumber) throws ExecutionException,
            InterruptedException;

    List<PokemonComplexItem> getListPokemonComplex();
}
