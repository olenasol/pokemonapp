package com.example.olena.pokemonapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.ui.fragments.BaseFragment;
import com.example.olena.pokemonapp.ui.fragments.PokemonListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(PokemonListFragment.getInstance(),true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_holder, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.commit();
    }
}
