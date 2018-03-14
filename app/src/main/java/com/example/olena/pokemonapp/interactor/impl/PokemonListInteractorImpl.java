package com.example.olena.pokemonapp.interactor.impl;

import android.util.Log;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.database.asynctasks.FillDatabaseAsyncTask;
import com.example.olena.pokemonapp.database.asynctasks.GetPokemonsAsyncTask;
import com.example.olena.pokemonapp.interactor.PokemonListInteractor;
import com.example.olena.pokemonapp.model.ListPageItem;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.database.PokemonService;
import com.example.olena.pokemonapp.model.PokemonSimpleItem;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.util.Constants;
import com.example.olena.pokemonapp.util.ImageUtil;
import com.example.olena.pokemonapp.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PokemonListInteractorImpl extends BaseInteractorImpl<PokemonListPresenter> implements PokemonListInteractor{

    private Subscription subscription;
    private List<PokemonComplexItem> listComplexPokemons;
    private int numberOfPokemons = Constants.ITEMS_PER_PAGE;
    private int numberOfLoadedPokemons;


    public PokemonListInteractorImpl(PokemonListPresenter pokemonListPresenter) {
        this.presenter = pokemonListPresenter;
    }

    private PokemonService createCallAPI(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.CONNECTION_TIMEOUT,TimeUnit.SECONDS).build();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PokemonService.class);
    }

    @Override
    public void retrieveListOfComplexPokemons(final int pageNumber) {
        subscription = createCallAPI()
                .getPokemonsListPage(Constants.ITEMS_PER_PAGE,pageNumber*Constants.ITEMS_PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListPageItem>() {

                    @Override
                    public void onCompleted() {
                        Log.i(Constants.APP_IDENTIFICATOR_LOG,"Page loaded.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(Constants.APP_IDENTIFICATOR_LOG,"Error loading page.");
                        showToast(R.string.err_loading);
                    }

                    @Override
                    public void onNext(ListPageItem listPageItem) {
                       getListOfPokemonsFromPageItem(listPageItem,pageNumber);
                    }
                });
    }

    @Override
    public void fillPokemonDb(List<PokemonComplexItem> list) {
        PokemonComplexItem[] arrPokemons = new PokemonComplexItem[list.size()];
        for(int i=0;i<arrPokemons.length;i++){
            arrPokemons[i]=list.get(i);
        }
        new FillDatabaseAsyncTask(AppDatabase.getAppDatabase(context())).execute(arrPokemons);
    }

    @Override
    public List<PokemonComplexItem> getPokemonsFromDb(int pageNumber)
            throws ExecutionException, InterruptedException {
        return new GetPokemonsAsyncTask(AppDatabase.getAppDatabase(context())).execute(pageNumber).get();
    }

    private void getListOfPokemonsFromPageItem(ListPageItem listPageItem,final int pageNumber) {
        listComplexPokemons = new ArrayList<>();
        for (final PokemonSimpleItem item:listPageItem.getListPokemonLinks()){
            subscription = createCallAPI()
                    .getPokemonById(Util.getIdFromLink(item.getPokemonDetailsUri()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PokemonComplexItem>() {

                        @Override
                        public void onCompleted() {
                            numberOfLoadedPokemons++;
                            Log.i(Constants.APP_IDENTIFICATOR_LOG,"Page loaded.");
                        }

                        @Override
                        public void onError(Throwable e) {
                            numberOfPokemons--;
                            numberOfLoadedPokemons++;
                            if(numberOfLoadedPokemons==Constants.ITEMS_PER_PAGE) {
                                presenter.processPokemonList(listComplexPokemons);
                            }
                            Log.i(Constants.APP_IDENTIFICATOR_LOG,"Error loading page.");
                        }

                        @Override
                        public void onNext(PokemonComplexItem pokemonComplexItem) {
                            pokemonComplexItem.setPokemonId(Util.getIdFromLink(item.getPokemonDetailsUri()));
                            pokemonComplexItem.setPageNumber(pageNumber);
                            try {
                                pokemonComplexItem.getSpritePokemon()
                                        .setImage(ImageUtil
                                                .getImgToByteFromURL(pokemonComplexItem
                                                        .getSpritePokemon().getFrontDefault()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            listComplexPokemons.add(pokemonComplexItem);
                            if(listComplexPokemons.size()==numberOfPokemons) {
                                presenter.processPokemonList(listComplexPokemons);
                            }
                        }
                    });
        }
    }

}
