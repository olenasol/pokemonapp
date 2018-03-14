package com.example.olena.pokemonapp.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.ui.adapters.PokemonPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
//TODO: 14.03.2018 change number of pages(from server)
public class PagerFragment extends BaseFragment<PokemonListPresenter> {

    @BindView(R.id.pager) ViewPager pager;
    private Unbinder unbinder;

    public static BaseFragment getInstance() {
        return new PagerFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pager;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        PagerAdapter pagerAdapter = new PokemonPagerAdapter(getChildFragmentManager(),30);
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
