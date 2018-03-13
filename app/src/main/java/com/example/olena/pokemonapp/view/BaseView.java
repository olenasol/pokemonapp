package com.example.olena.pokemonapp.view;

import android.content.Context;
import android.support.annotation.StringRes;

import com.example.olena.pokemonapp.ui.fragments.BaseFragment;

public interface BaseView {
    Context context();

    void showToast(@StringRes int stringId);

    void replaceFragment(BaseFragment fragment, boolean addToBackStack);

    void setCurrentFragment(BaseFragment fragment);

}
