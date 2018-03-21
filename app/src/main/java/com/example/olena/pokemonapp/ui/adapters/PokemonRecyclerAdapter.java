package com.example.olena.pokemonapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.ui.holders.PokemonViewHolder;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private PokemonListPresenter pokemonListPresenter;

    //TODO: package managment - put holder inside adapters package

    public PokemonRecyclerAdapter(PokemonListPresenter pokemonListPresenter) {
        this.pokemonListPresenter = pokemonListPresenter;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        pokemonListPresenter.onBindPokemonRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return pokemonListPresenter.getPokemonRowsCount();
    }
}
