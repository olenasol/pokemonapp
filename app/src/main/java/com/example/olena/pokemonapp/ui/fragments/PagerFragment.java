package com.example.olena.pokemonapp.ui.fragments;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.olena.pokemonapp.R;
import com.example.olena.pokemonapp.presenter.PokemonListPresenter;
import com.example.olena.pokemonapp.ui.adapters.PokemonPagerAdapter;
import com.example.olena.pokemonapp.util.Constants;
import com.example.olena.pokemonapp.view.PagerView;


import static android.support.v4.view.ViewPager.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends BaseFragment<PokemonListPresenter> implements PagerView {

    private ViewPager pager;
    private PokemonPagerAdapter pagerAdapter;
    private boolean isCorrectNumberOfPagesSet;

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
        pager = view.findViewById(R.id.pager);
        initPager();
    }

    private void initPager() {
        if(PreferenceManager.getDefaultSharedPreferences(getContext())
                .getInt(Constants.SAVED_PAGE_COUNT, 0)>0){
            pagerAdapter = new PokemonPagerAdapter(getChildFragmentManager(),
                   getCorrectPageNumber());
            isCorrectNumberOfPagesSet = true;
        } else {
            pagerAdapter = new PokemonPagerAdapter(getChildFragmentManager(), Constants.DEFAULT_PAGE_NUMBER);
            setPageChangeListener();
        }
        pager.setAdapter(pagerAdapter);
    }

    private void setPageChangeListener() {
        pager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(!isCorrectNumberOfPagesSet) {
                    if (PreferenceManager.getDefaultSharedPreferences(getContext())
                            .getInt(Constants.SAVED_PAGE_COUNT, 0) > 0) {
                        pagerAdapter.setPageCount(getCorrectPageNumber());
                    }
                    pagerAdapter.notifyDataSetChanged();
                    isCorrectNumberOfPagesSet = true;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int getCorrectPageNumber(){
        int numberOfItems = PreferenceManager.getDefaultSharedPreferences(getContext())
                .getInt(Constants.SAVED_PAGE_COUNT, 0);
        return (int)(Math.ceil(numberOfItems/(double)Constants.ITEMS_PER_PAGE));
    }

}
