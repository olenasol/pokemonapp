package com.example.olena.pokemonapp.database.asynctasks;

import android.os.AsyncTask;

import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.model.PokemonComplexItem;


public class GetPokemonByIdAsyncTask extends AsyncTask<Integer,Void,PokemonComplexItem> {

    private AppDatabase appDatabase;

    public GetPokemonByIdAsyncTask(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected PokemonComplexItem doInBackground(Integer... integers) {
        return appDatabase.pokemonComplexItemDao().loadPokemonById(integers[0]);
    }
}
