package com.example.olena.pokemonapp.ui.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.olena.pokemonapp.ui.fragments.PokemonListFragment;

public class PokemonPagerAdapter extends FragmentStatePagerAdapter {

    private int pageCount;

    public PokemonPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.pageCount = pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        return PokemonListFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + ++position;
    }


}
