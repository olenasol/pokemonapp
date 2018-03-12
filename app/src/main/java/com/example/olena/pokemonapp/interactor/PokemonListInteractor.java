package com.example.olena.pokemonapp.interactor;

import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.model.PokemonComplexItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PokemonListInteractor extends BaseInteractor{

    void retrieveListOfComplexPokemons();

    void fillPokemonDb(AppDatabase appDatabase, List<PokemonComplexItem> list);

    List<PokemonComplexItem> getPokemonsFromDb(AppDatabase appDatabase) throws ExecutionException,
            InterruptedException;
}
