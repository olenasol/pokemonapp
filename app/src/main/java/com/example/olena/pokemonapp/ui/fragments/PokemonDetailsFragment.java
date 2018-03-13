package com.example.olena.pokemonapp.ui.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonDetailsPresenter;
import com.example.olena.pokemonapp.presenter.impl.PokemonDetailsPresenterImpl;
import com.example.olena.pokemonapp.ui.adapters.ActivityRecyclerAdapter;
import com.example.olena.pokemonapp.view.PokemonDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetailsFragment extends BaseFragment<PokemonDetailsPresenter>  implements PokemonDetailsView {

    public static final String CHOOSEN_POKEMON_ID = "pokemon id";

    @BindView(R.id.pokemonImgViewDetails) ImageView pokemonImgView;
    @BindView(R.id.pokemonNameTxtDetails) TextView pokemonNameTxt;
    @BindView(R.id.weightTxt) TextView pokemonWeightTxt;
    @BindView(R.id.heightTxt) TextView pokemonHeightTxt;
    @BindView(R.id.activitiesResView) RecyclerView activitiesResView;
    private Unbinder unbinder;


    public static BaseFragment getInstance(int id) {
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(CHOOSEN_POKEMON_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pokemon_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        presenter = new PokemonDetailsPresenterImpl(this);
        setPokemonActivityList();
        presenter.loadPokemonData(getArguments().getInt(CHOOSEN_POKEMON_ID));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void setPokemonImg(Bitmap bitmap) {
        pokemonImgView.setImageBitmap(bitmap);
    }

    @Override
    public void setPokemonName(String name) {
        pokemonNameTxt.setText(name);
    }

    @Override
    public void setPokemonWeight(int weight) {
        pokemonWeightTxt.setText(String.valueOf(weight));
    }

    @Override
    public void setPokemonHeight(int height) {
        pokemonHeightTxt.setText(String.valueOf(height));
    }

    private void setPokemonActivityList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.Adapter recyclerViewAdapter = new ActivityRecyclerAdapter(presenter);
        activitiesResView.setLayoutManager(layoutManager);
        activitiesResView.setAdapter(recyclerViewAdapter);
    }


}
