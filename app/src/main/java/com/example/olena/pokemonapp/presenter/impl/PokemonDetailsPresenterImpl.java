package com.example.olena.pokemonapp.presenter.impl;


import com.example.olena.pokemonapp.interactor.PokemonDetailsInteractor;
import com.example.olena.pokemonapp.interactor.impl.PokemonDetailsInteractorImpl;
import com.example.olena.pokemonapp.model.ActivityWrapperItem;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.model.SpritePokemon;
import com.example.olena.pokemonapp.presenter.PokemonDetailsPresenter;
import com.example.olena.pokemonapp.util.ImageUtil;
import com.example.olena.pokemonapp.view.ActivityRowView;
import com.example.olena.pokemonapp.view.PokemonDetailsView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PokemonDetailsPresenterImpl extends BasePresenterImpl<PokemonDetailsView, PokemonDetailsInteractor>
        implements PokemonDetailsPresenter {

    private PokemonComplexItem pokemon;

    public PokemonDetailsPresenterImpl(PokemonDetailsView pokemonDetailsView) {
        this.view = pokemonDetailsView;
        this.interactor = new PokemonDetailsInteractorImpl(this);
       // pokemon = new PokemonComplexItem("",0,0,new SpritePokemon(""));
       // pokemon.setListOfActivities(new ArrayList<ActivityWrapperItem>());
    }

    @Override
    public void onBindActivityRowViewAtPosition(int position, ActivityRowView holder) {
        ActivityWrapperItem activityWrapperItem = pokemon.getListOfActivities().get(position);
        holder.setActivityNameText(activityWrapperItem.getActivityPokemon().getActivityName());
    }

    @Override
    public int getActivityRowsCount() {
        int n = pokemon.getListOfActivities().size();
        return pokemon.getListOfActivities().size();
    }

    @Override
    public void loadPokemonData(int id) {
        try {
            pokemon = interactor.getPokemonFromDbById(id);
            view.setPokemonImg(ImageUtil.byteArrToBitmap(pokemon.getSpritePokemon().getImage()));
            view.setPokemonName(pokemon.getPokemonName());
            view.setPokemonWeight(pokemon.getPokemonWeight());
            view.setPokemonHeight(pokemon.getPokemonHeight());
            //view.notifyAdapterSetChanged();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
