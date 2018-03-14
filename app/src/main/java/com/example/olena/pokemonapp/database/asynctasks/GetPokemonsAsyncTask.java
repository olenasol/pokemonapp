package com.example.olena.pokemonapp.database.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.olena.pokemonapp.database.AppDatabase;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.util.Constants;

import java.util.List;

public class GetPokemonsAsyncTask extends AsyncTask<Integer,Void,List<PokemonComplexItem>> {

    private AppDatabase appDatabase;

    public GetPokemonsAsyncTask(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }


    @Override
    protected List<PokemonComplexItem> doInBackground(Integer... integers) {
        return appDatabase.pokemonComplexItemDao().loadPokemonsByPage(integers[0]);
    }

    @Override
    protected void onPostExecute(List<PokemonComplexItem> list) {
        super.onPostExecute(list);
        Log.i(Constants.APP_IDENTIFICATOR_LOG,"Pokemons retrieved from db");
    }
}
