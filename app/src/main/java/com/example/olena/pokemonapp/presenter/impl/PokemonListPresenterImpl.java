package com.example.olena.pokemonapp.presenter.impl;

import android.view.View;

import com.example.olena.pokemonapp.interactor.PokemonListInteractor;
import com.example.olena.pokemonapp.interactor.impl.PokemonListInteractorImpl;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.ui.fragments.BaseFragment;
import com.example.olena.pokemonapp.ui.fragments.PokemonDetailsFragment;
import com.example.olena.pokemonapp.view.PokemonListView;
import com.example.olena.pokemonapp.view.PokemonRowView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PokemonListPresenterImpl extends BasePresenterImpl<PokemonListView, PokemonListInteractor>
        implements PokemonListPresenter {

    private int pageNumber;

    public PokemonListPresenterImpl(PokemonListView pokemonListView, int pageNumber) {
        this.view = pokemonListView;
        this.pageNumber = pageNumber;
        this.interactor = new PokemonListInteractorImpl(this);
    }

    @Override
    public void onBindPokemonRowViewAtPosition(int position, PokemonRowView holder) {
        final PokemonComplexItem pokemonComplexItem = interactor.getListPokemonComplex().get(position);
        holder.setPokemonNameText(pokemonComplexItem.getPokemonName());
        holder.setPokemonImg(pokemonComplexItem, context());
        holder.setPokemonItemOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment fragment = PokemonDetailsFragment.getInstance(pokemonComplexItem.getPokemonId());
                view.setCurrentFragment(fragment);
                view.replaceFragment(fragment, true);
            }
        });
    }

    @Override
    public int getPokemonRowsCount() {
        return interactor.getListPokemonComplex().size();
    }

    @Override
    public void processPokemonList(List<PokemonComplexItem> list) {
        interactor.fillPokemonDb(list);
        fillInPokemonList();
    }

    @Override
    public void getPokemonList() {
        try {
            interactor.getPokemonsFromDb(pageNumber);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (interactor.getListPokemonComplex() != null) {
            if (interactor.getListPokemonComplex().size() == 0) {
                view.isProgressbarVisible(true);
                interactor.retrieveListOfComplexPokemons(pageNumber);
            } else {
                fillInPokemonList();
            }
        }
    }

    @Override
    public void refetchPokemonsFromServer() {
        view.isProgressbarVisible(true);
        interactor.retrieveListOfComplexPokemons(pageNumber);
    }

    private void fillInPokemonList() {
        if (context() != null) {
            view.isProgressbarVisible(false);
            view.notifyAdapterSetChanged();
        }
    }


}
