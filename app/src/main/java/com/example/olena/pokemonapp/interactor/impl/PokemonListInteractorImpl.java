package com.example.olena.pokemonapp.interactor.impl;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.database.asynctasks.FillDatabaseAsyncTask;
import com.example.olena.pokemonapp.database.asynctasks.GetPokemonsAsyncTask;
import com.example.olena.pokemonapp.interactor.PokemonListInteractor;
import com.example.olena.pokemonapp.model.ListPageItem;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.model.PokemonSimpleItem;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.util.Constants;
import com.example.olena.pokemonapp.util.ImageUtil;
import com.example.olena.pokemonapp.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PokemonListInteractorImpl extends BaseInteractorImpl<PokemonListPresenter> implements PokemonListInteractor {

    private List<PokemonComplexItem> listComplexPokemons;
    private int numberOfPokemons ;
    private int numberOfLoadedPokemons;


    public PokemonListInteractorImpl(PokemonListPresenter pokemonListPresenter) {
        this.presenter = pokemonListPresenter;
    }


    @Override
    public void retrieveListOfComplexPokemons(final int pageNumber) {
        Util.createCallAPI()
                .getPokemonsListPage(Constants.ITEMS_PER_PAGE, pageNumber * Constants.ITEMS_PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListPageItem>() {

                    @Override
                    public void onCompleted() {
                        Log.i(Constants.APP_IDENTIFICATOR_LOG, "Page loaded.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(Constants.APP_IDENTIFICATOR_LOG, "Error loading page.");
                        showToast(R.string.err_loading);
                    }

                    @Override
                    public void onNext(ListPageItem listPageItem) {
                        savePageCount(listPageItem.getPageCount());
                        numberOfPokemons = listPageItem.getListPokemonLinks().size();
                        getListOfPokemonsFromPageItem(listPageItem, pageNumber);
                    }
                });
    }

    @Override
    public void fillPokemonDb(List<PokemonComplexItem> list) {
        PokemonComplexItem[] arrPokemons = new PokemonComplexItem[list.size()];
        for (int i = 0; i < arrPokemons.length; i++) {
            arrPokemons[i] = list.get(i);
        }
        if (context() != null) {
            new FillDatabaseAsyncTask(AppDatabase.getAppDatabase(context())).execute(arrPokemons);
        }
    }

    @Override
    public List<PokemonComplexItem> getPokemonsFromDb(int pageNumber)
            throws ExecutionException, InterruptedException {
        return new GetPokemonsAsyncTask(AppDatabase.getAppDatabase(context())).execute(pageNumber).get();
    }

    private void getListOfPokemonsFromPageItem(ListPageItem listPageItem, final int pageNumber) {
        listComplexPokemons = new ArrayList<>();
        for (final PokemonSimpleItem item : listPageItem.getListPokemonLinks()) {
            if (context() == null) {
                break;
            }
            //TODO: ну бляяяяяяяяяяяяяяяяяяяяяяяяя!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Util.createCallAPI()
                    .getPokemonById(Util.getIdFromLink(item.getPokemonDetailsUri()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PokemonComplexItem>() {

                        @Override
                        public void onCompleted() {
                            numberOfLoadedPokemons++;
                            Log.i(Constants.APP_IDENTIFICATOR_LOG, "Page loaded.");
                        }

                        @Override
                        public void onError(Throwable e) {
                            numberOfPokemons--;
                            numberOfLoadedPokemons++;
                            if (numberOfLoadedPokemons == Constants.ITEMS_PER_PAGE) {
                                presenter.processPokemonList(listComplexPokemons);
                            }
                            Log.i(Constants.APP_IDENTIFICATOR_LOG, "Error loading page.");
                        }

                        @Override
                        public void onNext(PokemonComplexItem pokemonComplexItem) {
                            pokemonComplexItem.setPokemonId(Util.getIdFromLink(item.getPokemonDetailsUri()));
                            pokemonComplexItem.setPageNumber(pageNumber);
                            pokemonComplexItem.getSpritePokemon()
                                    .setImage(ImageUtil
                                            .getImgToByteFromURL(pokemonComplexItem
                                                    .getSpritePokemon().getFrontDefault()));
                            listComplexPokemons.add(pokemonComplexItem);
                            if (listComplexPokemons.size() == numberOfPokemons) {
                                presenter.processPokemonList(listComplexPokemons);
                            }
                        }
                    });
        }
    }

    private void savePageCount(int pageCount) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Constants.SAVED_PAGE_COUNT, pageCount);
        editor.apply();
    }

}
