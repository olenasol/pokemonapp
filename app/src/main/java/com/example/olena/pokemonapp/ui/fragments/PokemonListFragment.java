package com.example.olena.pokemonapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.presenter.impl.PokemonListPresenterImpl;
import com.example.olena.pokemonapp.ui.adapters.PokemonRecyclerAdapter;
import com.example.olena.pokemonapp.view.PokemonListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PokemonListFragment extends BaseFragment<PokemonListPresenter>  implements PokemonListView{

    @BindView(R.id.pokemonListRecycler) RecyclerView pokemonListView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    private Unbinder unbinder;
    private RecyclerView.Adapter recyclerViewAdapter;

    public static BaseFragment getInstance() {
        return new PokemonListFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pokemon_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new PokemonListPresenterImpl(this);
        initializePokemonListView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializePokemonListView(View view) {
        unbinder = ButterKnife.bind(this,view);
        setHasOptionsMenu(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAdapter = new PokemonRecyclerAdapter(presenter);

        pokemonListView.setLayoutManager(layoutManager);
        pokemonListView.setAdapter(recyclerViewAdapter);
        presenter.getPokemonList();
    }

    @Override
    public void notifyAdapterSetChanged() {
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setProgressbarVisible() {
        progressBar.setVisibility(View.VISIBLE);
        pokemonListView.setVisibility(View.GONE);
    }

    @Override
    public void setRecyclerViewVisible() {
        progressBar.setVisibility(View.GONE);
        pokemonListView.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.refetchPokemonsFromServer();
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}