package com.example.olena.pokemonapp.presenter.impl;

import android.view.View;

import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.interactor.PokemonListInteractor;
import com.example.olena.pokemonapp.interactor.impl.PokemonListInteractorImpl;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.ui.fragments.PokemonDetailsFragment;
import com.example.olena.pokemonapp.view.PokemonListView;
import com.example.olena.pokemonapp.view.PokemonRowView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PokemonListPresenterImpl extends BasePresenterImpl<PokemonListView, PokemonListInteractor>
        implements PokemonListPresenter {

    private List<PokemonComplexItem> listOfPokemons;

    public PokemonListPresenterImpl(PokemonListView pokemonListView) {
        this.view = pokemonListView;
        this.interactor = new PokemonListInteractorImpl(this);
        listOfPokemons = new ArrayList<>();
    }

    @Override
    public void onBindPokemonRowViewAtPosition(int position, PokemonRowView holder) {
        final PokemonComplexItem pokemonComplexItem = listOfPokemons.get(position);
        holder.setPokemonNameText(pokemonComplexItem.getPokemonName());
        holder.setPokemonImg(pokemonComplexItem, context());
        holder.setPokemonItemOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.replaceFragment(PokemonDetailsFragment.getInstance(pokemonComplexItem.getPokemonId()),
                true);
            }
        });
    }

    @Override
    public int getPokemonRowsCount() {
        return listOfPokemons.size();
    }

    @Override
    public void processPokemonList(List<PokemonComplexItem> list) {
        interactor.fillPokemonDb(AppDatabase.getAppDatabase(context()), list);
        fillInPokemonList(list);

    }

    @Override
    public void getPokemonList() {
        List<PokemonComplexItem> list = null;
        try {
            list = interactor.getPokemonsFromDb(AppDatabase.getAppDatabase(context()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (list != null) {
            if (list.size() == 0) {
                view.setProgressbarVisible();
                interactor.retrieveListOfComplexPokemons();
            } else {
                fillInPokemonList(list);
            }
        }
    }

    @Override
    public void refetchPokemonsFromServer() {
        listOfPokemons = new ArrayList<>();
        view.setProgressbarVisible();
        interactor.retrieveListOfComplexPokemons();
    }

    private void fillInPokemonList(List<PokemonComplexItem> list) {
        if (list == null) {
            return;
        }
        listOfPokemons.addAll(list);
        view.setRecyclerViewVisible();
        view.notifyAdapterSetChanged();
    }


}
