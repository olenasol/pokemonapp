package com.example.olena.pokemonapp.presenter;


import android.content.Context;
import android.support.annotation.StringRes;

public interface BasePresenter {
    Context context();

    void showToast(@StringRes int stringId);

}
