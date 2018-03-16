package com.example.olena.pokemonapp.ui.activities;


import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.olena.pokemonapp.ui.fragments.BaseFragment;
import com.example.olena.pokemonapp.view.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected BaseFragment currentFragment;

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showToast(@StringRes int stringId) {
        Toast.makeText(getApplicationContext(), stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCurrentFragment(BaseFragment fragment) {
        this.currentFragment = fragment;
    }

}
