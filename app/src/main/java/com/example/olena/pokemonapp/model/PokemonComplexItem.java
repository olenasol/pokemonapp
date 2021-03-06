package com.example.olena.pokemonapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class PokemonComplexItem {

    @PrimaryKey
    private int pokemonId;
    @SerializedName(value = "name")
    private String pokemonName;
    @SerializedName(value = "weight")
    private int pokemonWeight;
    @SerializedName(value = "height")
    private int pokemonHeight;

    @Embedded
    @SerializedName(value = "sprites")
    private SpritePokemon spritePokemon;

    @TypeConverters(AbilityTypeConverter.class)
    @SerializedName(value = "abilities")
    private List<AbilityWrapperItem> listOfAbilities;

    @Expose(serialize = false, deserialize = false)
    private int pageNumber;

    @Ignore
    public PokemonComplexItem(String pokemonName, int pokemonWeight, int pokemonHeight,
                              SpritePokemon spritePokemon) {
        this.pokemonName = pokemonName;
        this.pokemonWeight = pokemonWeight;
        this.pokemonHeight = pokemonHeight;
        this.spritePokemon = spritePokemon;
    }

    public PokemonComplexItem(int pokemonId, String pokemonName, int pokemonWeight, int pokemonHeight,
                              SpritePokemon spritePokemon, List<AbilityWrapperItem> listOfAbilities) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.pokemonWeight = pokemonWeight;
        this.pokemonHeight = pokemonHeight;
        this.spritePokemon = spritePokemon;
        this.listOfAbilities = listOfAbilities;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public void setPokemonWeight(int pokemonWeight) {
        this.pokemonWeight = pokemonWeight;
    }

    public void setPokemonHeight(int pokemonHight) {
        this.pokemonHeight = pokemonHight;
    }

    public void setSpritePokemon(SpritePokemon spritePokemon) {
        this.spritePokemon = spritePokemon;
    }
    public List<AbilityWrapperItem> getListOfAbilities() {
        return listOfAbilities;
    }

    public void setListOfAbilities(List<AbilityWrapperItem> listOfAbilities) {
        this.listOfAbilities = listOfAbilities;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int getPokemonWeight() {
        return pokemonWeight;
    }

    public int getPokemonHeight() {
        return pokemonHeight;
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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
