package com.example.olena.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class PokemonSimpleItem {

    //TODO: 21/03/2018 set field
    private int pokemonId;

    @SerializedName(value = "url")
    private String pokemonDetailsUri;

    public String getPokemonDetailsUri() {
        return pokemonDetailsUri;
    }

    public void setPokemonDetailsUri(String pokemonDetailsUri) {
        this.pokemonDetailsUri = pokemonDetailsUri;
    }

    public int getPokemonId() {
        return getIdFromLink();
    }

    private int getIdFromLink(){
        String tempUri = pokemonDetailsUri;
        if (tempUri != null && tempUri.length() > 0 && tempUri.charAt(tempUri.length() - 1) == '/') {
            tempUri = tempUri.substring(0,tempUri.length() - 1);
        }
        int slashPosition = 0;
        if(tempUri != null) {
            for (int i = tempUri.length() - 1; i >= 0; i--) {
                if (tempUri.charAt(i) == '/') {
                    slashPosition = i;
                    break;
                }
            }
            tempUri = tempUri.substring(slashPosition + 1);
        }
        return Integer.parseInt(tempUri);
    }
}
