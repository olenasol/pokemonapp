package com.example.olena.pokemonapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.olena.pokemonapp.model.PokemonComplexItem;

import java.util.List;

/**
 * Created by olena on 3/8/2018.
 */

@Dao
public interface PokemonComplexItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComplexPokemons(PokemonComplexItem... pokemons);

    @Delete
    void deleteComplexPokemons(PokemonComplexItem... pokemons);

    @Query("SELECT * FROM PokemonComplexItem")
    List<PokemonComplexItem> loadAllPokemonComplex();

    @Query("SELECT * FROM PokemonComplexItem WHERE pokemonId=id")
    PokemonComplexItem loadComplexPokemon(int id);

}
