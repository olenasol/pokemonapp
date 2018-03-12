package com.example.olena.pokemonapp.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.view.ActivityRowView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityViewHolder  extends RecyclerView.ViewHolder implements ActivityRowView {

    @BindView(R.id.activityTxtView) TextView activityNameTxt;

    public ActivityViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setActivityNameText(String activityName) {
        activityNameTxt.setText(activityName);
    }
}
