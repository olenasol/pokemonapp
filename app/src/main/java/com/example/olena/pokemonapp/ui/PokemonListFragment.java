package com.example.olena.pokemonapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.interactor.impl.PokemonListInteractorImpl;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.presenter.impl.PokemonListPresenterImpl;
import com.example.olena.pokemonapp.view.PokemonListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonListFragment extends Fragment  implements PokemonListView{

    @BindView(R.id.pokemonListRecycler) RecyclerView pokemonListView;
    private Unbinder unbinder;
    private PokemonListPresenter pokemonListPresenter;
    private RecyclerView.Adapter recyclerViewAdapter;

    public static PokemonListFragment getInstance() {
        return new PokemonListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pokemonListPresenter = new PokemonListPresenterImpl(this);
        initializePokemonListView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initializePokemonListView(View view) {
        unbinder = ButterKnife.bind(this,view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAdapter = new PokemonRecyclerAdapter(pokemonListPresenter,getContext());

        pokemonListView.setLayoutManager(layoutManager);
        pokemonListView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void notifyAdapterSetChanged() {
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
