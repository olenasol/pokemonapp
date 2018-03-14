package com.example.olena.pokemonapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.ui.fragments.BaseFragment;
import com.example.olena.pokemonapp.ui.fragments.PagerFragment;
import com.example.olena.pokemonapp.ui.fragments.PokemonDetailsFragment;
import com.example.olena.pokemonapp.ui.fragments.PokemonListFragment;
import com.example.olena.pokemonapp.util.Constants;

public class MainActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            Fragment fragment = getSupportFragmentManager().getFragment(savedInstanceState, Constants.SAVED_FRAGMENT);
            currentFragment = (BaseFragment)fragment;
            setCurrentFragment(currentFragment);
        }
        else {
            currentFragment = PagerFragment.getInstance();
            setCurrentFragment(currentFragment);
        }
        replaceFragment(currentFragment,false);

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, Constants.SAVED_FRAGMENT,currentFragment);

        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            retrieveFragmentInHolder();
        } else {
            getFragmentManager().popBackStack();
        }

    }

    @Override
    public void onBackStackChanged() {
        retrieveFragmentInHolder();
    }

    private void retrieveFragmentInHolder(){
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_holder);
        setCurrentFragment(fragment);
    }
}
