package com.example.olena.pokemonapp.util;


import com.example.olena.pokemonapp.services.PokemonService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {

    public static int getIdFromLink(String str){
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '/') {
            str = str.substring(0, str.length() - 1);
        }
        int slashPosition = 0;
        if(str != null) {
            for (int i = str.length() - 1; i >= 0; i--) {
                if (str.charAt(i) == '/') {
                    slashPosition = i;
                    break;
                }
            }
            str = str.substring(slashPosition + 1);
        }
        return Integer.parseInt(str);
    }

    public static PokemonService createCallAPI(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.CONNECTION_TIMEOUT,TimeUnit.SECONDS).build();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PokemonService.class);
    }
}
