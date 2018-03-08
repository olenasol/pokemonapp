package com.example.olena.pokemonapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.view.PokemonRowView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by olena on 3/5/2018.
 */

public class PokemonViewHolder extends RecyclerView.ViewHolder implements PokemonRowView{

    @BindView(R.id.pokemonImgView) ImageView pokemonImgView;
    @BindView(R.id.pokemonNameTxt) TextView pokemonNameTxt;
    @BindView(R.id.heightTextView) TextView pokemonHeightTxt;
    @BindView(R.id.weightTextView) TextView pokemonWeightTxt;

    public PokemonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setPokemonNameText(String pokemonName) {
        pokemonNameTxt.setText(pokemonName);
    }

    @Override
    public void setPokemonHeightText(String pokemonHeight) {
        pokemonHeightTxt.setText(pokemonHeight);
    }

    @Override
    public void setPokemonWeightText(String pokemonWeight) {
        pokemonWeightTxt.setText(pokemonWeight);
    }

    @Override
    public void setPokemonImg(String pokemonImgUrl, Context context) {
        Picasso.with(context)
                .load(pokemonImgUrl)
                .into(pokemonImgView);
    }
}
