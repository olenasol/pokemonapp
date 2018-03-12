package com.example.olena.pokemonapp.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olena.pokemonapp.presenter.BasePresenter;
import com.example.olena.pokemonapp.ui.activities.BaseActivity;
import com.example.olena.pokemonapp.view.BaseView;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    @Override
    public void replaceFragment(BaseFragment fragment, boolean addToBackStack) {
        if(( getActivity()) != null)
        ((BaseActivity) getActivity()).replaceFragment(fragment, addToBackStack);
    }

    @Override
    public Context context() {
        return ((BaseActivity) getActivity()).context();
    }

    @Override
    public void showToast(@StringRes int stringId) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showToast(stringId);
        }
    }
}