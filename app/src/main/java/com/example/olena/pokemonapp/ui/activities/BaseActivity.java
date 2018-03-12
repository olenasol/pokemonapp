package com.example.olena.pokemonapp.ui.activities;


import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.olena.pokemonapp.view.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showToast(@StringRes int stringId) {
        Toast.makeText(getApplicationContext(), stringId, Toast.LENGTH_SHORT).show();
    }
}
