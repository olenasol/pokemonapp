package com.example.olena.pokemonapp.interactor.impl;


import android.content.Context;
import android.support.annotation.StringRes;

import com.example.olena.pokemonapp.interactor.BaseInteractor;
import com.example.olena.pokemonapp.presenter.BasePresenter;

public abstract class BaseInteractorImpl<P extends BasePresenter> implements BaseInteractor {

    protected P presenter;


    public Context context() {
        return presenter.context();
    }

    @Override
    public void showToast(@StringRes int stringId) {
        presenter.showToast(stringId);
    }
}
