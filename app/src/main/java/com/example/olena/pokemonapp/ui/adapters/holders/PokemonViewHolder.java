package com.example.olena.pokemonapp.ui.adapters.holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.model.PokemonComplexItem;
import com.example.olena.pokemonapp.util.ImageUtil;
import com.example.olena.pokemonapp.view.PokemonRowView;
import com.squareup.picasso.Picasso;

public class PokemonViewHolder extends RecyclerView.ViewHolder implements PokemonRowView {

    private ImageView pokemonImgView;
    private TextView pokemonNameTxt;
    private CardView pokemonCardView;

    public PokemonViewHolder(View itemView) {
        super(itemView);
        pokemonImgView = itemView.findViewById(R.id.pokemonImgView);
        pokemonNameTxt = itemView.findViewById(R.id.pokemonNameTxt);
        pokemonCardView = itemView.findViewById(R.id.cardViewPokemon);
    }

    @Override
    public void setPokemonNameText(String pokemonName) {
        pokemonNameTxt.setText(pokemonName);
    }


    @Override
    public void setPokemonImg(PokemonComplexItem complexItem, Context context) {
        if (complexItem.getSpritePokemon().getImage().length == 0) {
            Picasso.with(context)
                    .load(complexItem.getSpritePokemon().getFrontDefault())
                    .into(pokemonImgView);
        } else {
            Bitmap bitmap = ImageUtil.byteArrToBitmap(complexItem.getSpritePokemon().getImage());
            pokemonImgView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void setPokemonItemOnClick(View.OnClickListener onClick) {
        pokemonCardView.setOnClickListener(onClick);
    }

}
