package com.example.olena.pokemonapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonDetailsPresenter;
import com.example.olena.pokemonapp.ui.adapters.holders.AbilityViewHolder;

public class ActivityRecyclerAdapter extends RecyclerView.Adapter<AbilityViewHolder>{

    private PokemonDetailsPresenter pokemonDetailsPresenter;

    public ActivityRecyclerAdapter(PokemonDetailsPresenter pokemonDetailsPresenter) {
        this.pokemonDetailsPresenter = pokemonDetailsPresenter;
    }

    @NonNull
    @Override
    public AbilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new AbilityViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AbilityViewHolder holder, int position) {
        pokemonDetailsPresenter.onBindActivityRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return pokemonDetailsPresenter.getActivityRowsCount();
    }
}
