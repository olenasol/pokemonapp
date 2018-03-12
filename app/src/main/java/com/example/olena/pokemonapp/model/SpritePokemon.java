package com.example.olena.pokemonapp.model;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpritePokemon {
    @SerializedName(value = "front_default")
    private String frontDefault;


    @Expose(serialize = false, deserialize = false)
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public SpritePokemon(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
