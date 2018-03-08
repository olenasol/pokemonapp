package com.example.olena.pokemonapp.model;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by olena on 3/5/2018.
 */

public interface PokemonService {
    @GET("pokemon/")
    Observable<ListPageItem> getPokemonsListPage(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{pokemon_id}")
    Observable<PokemonComplexItem> getPokemonById(@Path("pokemon_id") int pokemonId);
}
