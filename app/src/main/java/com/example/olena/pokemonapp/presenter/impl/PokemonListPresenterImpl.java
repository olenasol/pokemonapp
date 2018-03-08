package com.example.olena.pokemonapp.presenter.impl;

import android.content.Context;

import com.example.olena.pokemonapp.interactor.PokemonListInteractor;
import com.example.olena.pokemonapp.interactor.impl.PokemonListInteractorImpl;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.model.SpritePokemon;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.ui.MainActivity;
import com.example.olena.pokemonapp.view.PokemonListView;
import com.example.olena.pokemonapp.view.PokemonRowView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olena on 3/5/2018.
 */

public class PokemonListPresenterImpl implements PokemonListPresenter {

    private PokemonListView pokemonListView;
    private PokemonListInteractor pokemonListInteractor;
    private List<PokemonComplexItem> listOfPokemons;

    public PokemonListPresenterImpl(PokemonListView pokemonListView) {
        this.pokemonListView = pokemonListView;
        this.pokemonListInteractor = new PokemonListInteractorImpl(this);
       // listOfPokemons = getMockdedList();// new ArrayList<>();
        listOfPokemons = new ArrayList<>();
        pokemonListInteractor.retrieveListOfComplexPokemons();
    }

    public List<PokemonComplexItem> getMockdedList() {
        List<PokemonComplexItem> list =new ArrayList<>();
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")));
        list.add(new PokemonComplexItem("ivysaur",130,10,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png")));
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png")));
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")));
        list.add(new PokemonComplexItem("ivysaur",130,10,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png")));
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png")));
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")));
        list.add(new PokemonComplexItem("ivysaur",130,10,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png")));
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png")));
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")));
        list.add(new PokemonComplexItem("ivysaur",130,10,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png")));
        list.add(new PokemonComplexItem("bulbasaur",69,7,
                new SpritePokemon("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png")));
        return list;
    }
    @Override
    public void onBindPokemonRowViewAtPosition(int position, PokemonRowView holder, Context context) {
        PokemonComplexItem pokemonComplexItem = listOfPokemons.get(position);
        holder.setPokemonNameText(pokemonComplexItem.getPokemonName());
        holder.setPokemonHeightText(String.valueOf(pokemonComplexItem.getPokemonHight()));
        holder.setPokemonWeightText(String.valueOf(pokemonComplexItem.getPokemonWeight()));
        holder.setPokemonImg(pokemonComplexItem.getSpritePokemon().getFrontDefault(),context);
    }

    @Override
    public int getPokemonRowsCount() {
        return listOfPokemons.size();
    }

    @Override
    public void fillInPokemonList(List<PokemonComplexItem> list) {
        if (list == null) {
            return;
        }
        listOfPokemons.addAll(list);
        pokemonListView.notifyAdapterSetChanged();
    }
}
