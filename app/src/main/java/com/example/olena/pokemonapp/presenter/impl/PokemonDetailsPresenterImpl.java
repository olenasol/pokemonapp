package com.example.olena.pokemonapp.presenter.impl;


import com.example.olena.pokemonapp.interactor.PokemonDetailsInteractor;
import com.example.olena.pokemonapp.interactor.impl.PokemonDetailsInteractorImpl;
import com.example.olena.pokemonapp.model.AbilityWrapperItem;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.presenter.PokemonDetailsPresenter;
import com.example.olena.pokemonapp.util.ImageUtil;
import com.example.olena.pokemonapp.view.ActivityRowView;
import com.example.olena.pokemonapp.view.PokemonDetailsView;

import java.util.concurrent.ExecutionException;

public class PokemonDetailsPresenterImpl extends BasePresenterImpl<PokemonDetailsView, PokemonDetailsInteractor>
        implements PokemonDetailsPresenter {

    private PokemonComplexItem pokemon;

    public PokemonDetailsPresenterImpl(PokemonDetailsView pokemonDetailsView) {
        this.view = pokemonDetailsView;
        this.interactor = new PokemonDetailsInteractorImpl(this);
       // pokemon = new PokemonComplexItem("",0,0,new SpritePokemon(""));
       // pokemon.setListOfAbilities(new ArrayList<AbilityWrapperItem>());
    }

    @Override
    public void onBindActivityRowViewAtPosition(int position, ActivityRowView holder) {
        AbilityWrapperItem abilityWrapperItem = pokemon.getListOfAbilities().get(position);
        holder.setActivityNameText(abilityWrapperItem.getAbilityPokemon().getAbilityName());
    }

    @Override
    public int getActivityRowsCount() {
        return pokemon.getListOfAbilities().size();
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
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
