package com.example.olena.pokemonapp.database.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.util.Constants;

public class FillDatabaseAsyncTask extends AsyncTask<PokemonComplexItem,Void,Void> {

    private AppDatabase appDatabase;

    public FillDatabaseAsyncTask(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    protected Void doInBackground(PokemonComplexItem... pokemonComplexItems) {
        fillInDatabase(pokemonComplexItems);
        return null;
    }
    @Override
    protected void onPostExecute(Void results) {
        Log.i(Constants.APP_IDENTIFICATOR_LOG,"Database filled");
    }
    private void fillInDatabase(PokemonComplexItem... list){
        appDatabase.pokemonComplexItemDao().deleteAllPokemonComplex();
        appDatabase.pokemonComplexItemDao().insertComplexPokemons(list);
    }
}