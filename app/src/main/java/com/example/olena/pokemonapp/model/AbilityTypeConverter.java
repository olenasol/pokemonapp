package com.example.olena.pokemonapp.model;


import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AbilityTypeConverter {
    @TypeConverter
    public static List<AbilityWrapperItem> stringToAbilities(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<AbilityWrapperItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String abilitiesToString(List<AbilityWrapperItem> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<AbilityWrapperItem>>() {}.getType();
        return gson.toJson(list, type);
    }
}
