package com.example.olena.pokemonapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.presenter.impl.PokemonListPresenterImpl;
import com.example.olena.pokemonapp.ui.adapters.PokemonRecyclerAdapter;
import com.example.olena.pokemonapp.util.Constants;
import com.example.olena.pokemonapp.view.PokemonListView;


public class PokemonListFragment extends BaseFragment<PokemonListPresenter> implements PokemonListView {

    private static final String PAGE_ID = "page_id";

    private RecyclerView pokemonRecycler;
    private ProgressBar progressBar;

    public static BaseFragment getInstance(int pageNum) {
        BaseFragment fragment = new PokemonListFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_ID, pageNum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pokemon_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            presenter = new PokemonListPresenterImpl(this, getArguments().getInt(PAGE_ID, 0));
        }
        initUI(view);
        initializePokemonListView();
        if (savedInstanceState != null) {
            pokemonRecycler.scrollToPosition(savedInstanceState.getInt(Constants.LIST_POS));
        }
    }

    private void initUI(View view) {
        pokemonRecycler = view.findViewById(R.id.pokemonListRecycler);
        progressBar = view.findViewById(R.id.progressBar);
        setHasOptionsMenu(true);
    }

    private void initializePokemonListView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.Adapter recyclerViewAdapter = new PokemonRecyclerAdapter(presenter);

        pokemonRecycler.setLayoutManager(layoutManager);
        pokemonRecycler.setAdapter(recyclerViewAdapter);
        presenter.getPokemonList();
    }

    @Override
    public void notifyAdapterSetChanged() {
        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        pokemonRecycler.setLayoutAnimation(controller);
        pokemonRecycler.getAdapter().notifyDataSetChanged();
        pokemonRecycler.scheduleLayoutAnimation();
    }

    @Override
    public void isProgressbarVisible(boolean isVisible) {
        if(isVisible) {
            progressBar.setVisibility(View.VISIBLE);
            pokemonRecycler.setVisibility(View.GONE);
        }
        else {
            progressBar.setVisibility(View.GONE);
            pokemonRecycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.refetchPokemonsFromServer();
                pokemonRecycler.getLayoutManager().scrollToPosition(0);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (pokemonRecycler != null) {
            outState.putInt(Constants.LIST_POS, ((LinearLayoutManager) pokemonRecycler.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition());
        }
        super.onSaveInstanceState(outState);
    }
}
