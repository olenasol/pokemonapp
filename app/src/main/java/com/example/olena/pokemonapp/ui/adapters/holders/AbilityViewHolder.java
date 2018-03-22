package com.example.olena.pokemonapp.ui.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.view.ActivityRowView;

public class AbilityViewHolder extends RecyclerView.ViewHolder implements ActivityRowView {

    private TextView activityNameTxt;

    public AbilityViewHolder(View itemView) {
        super(itemView);
        activityNameTxt =itemView.findViewById(R.id.activityTxtView);
    }

    @Override
    public void setActivityNameText(String activityName) {
        activityNameTxt.setText(activityName);
    }
}
