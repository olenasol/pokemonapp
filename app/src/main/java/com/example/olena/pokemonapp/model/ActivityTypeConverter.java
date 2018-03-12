package com.example.olena.pokemonapp.model;


import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ActivityTypeConverter {
    @TypeConverter
    public static List<ActivityWrapperItem> stringToMActivities(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ActivityWrapperItem>>() {}.getType();
        List<ActivityWrapperItem> activities= gson.fromJson(json, type);
        return activities;
    }

    @TypeConverter
    public static String activitiesToString(List<ActivityWrapperItem> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ActivityWrapperItem>>() {}.getType();
        String json = gson.toJson(list, type);
        return json;
    }
}
