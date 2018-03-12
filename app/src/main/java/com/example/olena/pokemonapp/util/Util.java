package com.example.olena.pokemonapp.util;



public class Util {

    public static int getIdFromLink(String str){
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '/') {
            str = str.substring(0, str.length() - 1);
        }
        int slashPosition = 0;
        if(str!=null) {
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
}
