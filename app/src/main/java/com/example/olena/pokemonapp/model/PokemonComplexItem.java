package com.example.olena.pokemonapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olena on 3/5/2018.
 */
@Entity
public class PokemonComplexItem {

    @PrimaryKey
    private int pokemonId;
    @SerializedName(value = "name")
    private String pokemonName;
    @SerializedName(value = "weight")
    private int pokemonWeight;
    @SerializedName(value = "height")
    private int pokemonHight;

    @Embedded
    @SerializedName(value = "sprites")
    private SpritePokemon spritePokemon;

    public PokemonComplexItem(String pokemonName, int pokemonWeight, int pokemonHight, SpritePokemon spritePokemon) {
        this.pokemonName = pokemonName;
        this.pokemonWeight = pokemonWeight;
        this.pokemonHight = pokemonHight;
        this.spritePokemon = spritePokemon;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int getPokemonWeight() {
        return pokemonWeight;
    }

    public int getPokemonHight() {
        return pokemonHight;
    }

    public SpritePokemon getSpritePokemon() {
        return spritePokemon;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }
}
