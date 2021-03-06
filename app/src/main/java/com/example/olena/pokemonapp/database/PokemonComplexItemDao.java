package com.example.olena.pokemonapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.olena.pokemonapp.model.PokemonComplexItem;

import java.util.List;

@Dao
public interface PokemonComplexItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComplexPokemons(PokemonComplexItem... pokemons);

    @Query("SELECT * FROM PokemonComplexItem WHERE pokemonId = :id")
    PokemonComplexItem loadPokemonById(int id);

    @Query("DELETE FROM PokemonComplexItem WHERE pageNumber = :pageNumber")
    void deletePokemonComplexPage(int pageNumber);

    @Query("SELECT * FROM PokemonComplexItem WHERE pageNumber = :pageNum")
    List<PokemonComplexItem> loadPokemonsByPage(int pageNum);


}
