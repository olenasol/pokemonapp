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
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.presenter.PokemonDetailsPresenter;
import com.example.olena.pokemonapp.presenter.impl.PokemonDetailsPresenterImpl;
import com.example.olena.pokemonapp.ui.adapters.ActivityRecyclerAdapter;
import com.example.olena.pokemonapp.view.PokemonDetailsView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetailsFragment extends BaseFragment<PokemonDetailsPresenter>  implements PokemonDetailsView {

    private static final String CHOOSEN_POKEMON_ID = "pokemon id";

    private ImageView pokemonImgView;
    private TextView pokemonNameTxt;
    private TextView pokemonWeightTxt;
    private TextView pokemonHeightTxt;
    private RecyclerView activitiesResView;

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
        initUI(view);
        presenter = new PokemonDetailsPresenterImpl(this);
        setPokemonActivityList();
        if(getArguments()!=null) {
            presenter.loadPokemonData(getArguments().getInt(CHOOSEN_POKEMON_ID));
        }
    }

    private void initUI(View view) {
        pokemonImgView = view.findViewById(R.id.pokemonImgViewDetails);
        pokemonNameTxt = view.findViewById(R.id.pokemonNameTxtDetails);
        pokemonWeightTxt = view.findViewById(R.id.weightTxt);
        pokemonHeightTxt = view.findViewById(R.id.heightTxt);
        activitiesResView =view.findViewById(R.id.activitiesResView);
    }

    @Override
    public void setPokemonUI(PokemonComplexItem pokemonItem) {
        pokemonNameTxt.setText(pokemonItem.getPokemonName());
        pokemonHeightTxt.setText(String.valueOf(pokemonItem.getPokemonHeight()));
        pokemonWeightTxt.setText(String.valueOf(pokemonItem.getPokemonWeight()));
    }

    @Override
    public void setPokemonImg(Bitmap bitmap) {
        pokemonImgView.setImageBitmap(bitmap);
    }


    private void setPokemonActivityList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.Adapter recyclerViewAdapter = new ActivityRecyclerAdapter(presenter);
        activitiesResView.setLayoutManager(layoutManager);
        activitiesResView.setAdapter(recyclerViewAdapter);
    }


}
