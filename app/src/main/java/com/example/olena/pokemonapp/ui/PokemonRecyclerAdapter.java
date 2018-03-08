package com.example.olena.pokemonapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;

/**
 * Created by olena on 3/5/2018.
 */

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private PokemonListPresenter pokemonListPresenter;
    private Context context;

    public PokemonRecyclerAdapter(PokemonListPresenter pokemonListPresenter, Context context) {
        this.pokemonListPresenter = pokemonListPresenter;
        this.context= context;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_item,parent,false));
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        pokemonListPresenter.onBindPokemonRowViewAtPosition(position, holder,context);

    }

    @Override
    public int getItemCount() {
        return pokemonListPresenter.getPokemonRowsCount();
    }
}
