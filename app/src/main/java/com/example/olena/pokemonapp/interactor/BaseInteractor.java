package com.example.olena.pokemonapp.interactor;

import android.content.Context;
import android.support.annotation.StringRes;

public interface BaseInteractor {
    Context context();

    void showToast(@StringRes int stringId);
}
