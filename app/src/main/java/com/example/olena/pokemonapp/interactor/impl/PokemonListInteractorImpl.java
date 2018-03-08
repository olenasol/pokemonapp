package com.example.olena.pokemonapp.interactor.impl;

import android.util.Log;

import com.example.olena.pokemonapp.interactor.PokemonListInteractor;
import com.example.olena.pokemonapp.model.ListPageItem;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.model.PokemonService;
import com.example.olena.pokemonapp.model.PokemonSimpleItem;
import com.example.olena.pokemonapp.model.SpritePokemon;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.util.Constraints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by olena on 3/5/2018.
 */

public class PokemonListInteractorImpl implements PokemonListInteractor{

    private Subscription subscription;
    private PokemonListPresenter pokemonListPresenter;
    private List<PokemonComplexItem> listComplexPokemons;
    private int numberOfPokemons = 20;
    private int numberOfLoadedPokemons;


    public PokemonListInteractorImpl(PokemonListPresenter pokemonListPresenter) {
        this.pokemonListPresenter = pokemonListPresenter;
    }

    private PokemonService createCallAPI(){
        return new Retrofit.Builder()
                .baseUrl(Constraints.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PokemonService.class);
    }

    @Override
    public void retrieveListOfComplexPokemons() {
        subscription = createCallAPI()
                .getPokemonsListPage(20,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListPageItem>() {

                    @Override
                    public void onCompleted() {
                        Log.i(Constraints.APP_IDENTIFICATOR_LOG,"Page loaded.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(Constraints.APP_IDENTIFICATOR_LOG,"Error loading page.");
                    }

                    @Override
                    public void onNext(ListPageItem listPageItem) {
                       getListOfPokemonsFromPageItem(listPageItem);
                    }
                });
    }

    private void getListOfPokemonsFromPageItem(ListPageItem listPageItem) {
        listComplexPokemons = new ArrayList<>();
        for (PokemonSimpleItem item:listPageItem.getListPokemonLinks()){
            subscription = createCallAPI()
                    .getPokemonById(getIdFromLink(item.getPokemonDetailsUri()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PokemonComplexItem>() {

                        @Override
                        public void onCompleted() {
                            numberOfLoadedPokemons++;
                            Log.i(Constraints.APP_IDENTIFICATOR_LOG,"Page loaded.");
                        }

                        @Override
                        public void onError(Throwable e) {
                            numberOfPokemons--;
                            numberOfLoadedPokemons++;
                            if(numberOfLoadedPokemons==20) {
                                pokemonListPresenter.fillInPokemonList(listComplexPokemons);
                            }
                            Log.i(Constraints.APP_IDENTIFICATOR_LOG,"Error loading page.");
                        }

                        @Override
                        public void onNext(PokemonComplexItem pokemonComplexItem) {
                            listComplexPokemons.add(pokemonComplexItem);
                            if(listComplexPokemons.size()==numberOfPokemons) {
                                 pokemonListPresenter.fillInPokemonList(listComplexPokemons);
                            }
                        }
                    });
        }
    }



    private int getIdFromLink(String str){
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '/') {
            str = str.substring(0, str.length() - 1);
        }
        int slashPosition = 0;
        for (int i = str.length()-1;i >= 0;i--){
            if(str.charAt(i)=='/'){
                slashPosition = i;
                break;
            }
        }
        str = str.substring(slashPosition+1);
        return Integer.parseInt(str);
    }
}
