package com.example.olena.pokemonapp.interactor.impl;

import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.database.asynctasks.GetPokemonByIdAsyncTask;
import com.example.olena.pokemonapp.database.asynctasks.GetPokemonsAsyncTask;
import com.example.olena.pokemonapp.interactor.PokemonDetailsInteractor;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.presenter.PokemonDetailsPresenter;

import java.util.concurrent.ExecutionException;

public class PokemonDetailsInteractorImpl  extends BaseInteractorImpl<PokemonDetailsPresenter>
        implements PokemonDetailsInteractor {

    public PokemonDetailsInteractorImpl(PokemonDetailsPresenter pokemonDetailsPresenter) {
        this.presenter = pokemonDetailsPresenter;
    }

    @Override
    public PokemonComplexItem getPokemonFromDbById(int id) throws ExecutionException, InterruptedException {
            return new GetPokemonByIdAsyncTask(AppDatabase.getAppDatabase(context())).execute(id).get();
    }
}
