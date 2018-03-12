package com.example.olena.pokemonapp.presenter.impl;


import android.content.Context;
import android.support.annotation.StringRes;

import com.example.olena.pokemonapp.interactor.BaseInteractor;
import com.example.olena.pokemonapp.presenter.BasePresenter;
import com.example.olena.pokemonapp.view.BaseView;

public abstract class BasePresenterImpl<V extends BaseView, I extends BaseInteractor> implements BasePresenter {

    protected V view;
    protected I interactor;


    @Override
    public Context context() {
        return view.context();
    }

    @Override
    public void showToast(@StringRes int stringId) {
        view.showToast(stringId);
    }
}

