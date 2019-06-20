package com.app.sample.recipe.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.sample.recipe.Conexao.Models.Publicacoes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoriteUtils {

    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    private static String KEY_LIS_ID = "KEY_LIS_ID";

    //==============================================================================================
    //
    // Init Functions
    //
    //==============================================================================================

    public static void addFavoriteId(Context ctx, String id) {
        if (!isIdExist(ctx, id)) {
            List<String> listId = getListId(ctx);
            listId.add(id);
            saveListId(listId, ctx);
        }
    }

    public static void delFavoriteId(Context ctx, String id) {
        List<String> listId = getListId(ctx);
        for (int i = 0; i < listId.size(); i++) {
            if (listId.get(i).equals(id)) {
                listId.remove(i);
            }
        }
        saveListId(listId, ctx);
    }

    //==============================================================================================
    //
    // Verify Id Existes in SharedPreferences
    //
    //==============================================================================================

    public static boolean isIdExist(Context ctx, String id) {
        List<String> listId = getListId(ctx);
        for (int i = 0; i < listId.size(); i++) {
            if (listId.get(i).equals(id)) {
                return true;
            }
        }
        return false;
    }

    //==============================================================================================
    //
    // Get List ID
    //
    //==============================================================================================

//    public static List<String> getFavorites(Context ctx) {
//        return getListId(ctx);
//    }

    private static List<String> getListId(Context context) {
        String s = getStringPref(KEY_LIS_ID, "null", context);
        return split(s);
    }

    private static List<String> split(String s) {
        // split by "|"
        List<String> list = new ArrayList<>();
        if (!s.equals("null")) {
            for (int i = 0; i < s.split("\\|").length; i++) {
                list.add(s.split("\\|")[i]);
            }
        }
        return list;
    }
    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    private static void saveListId(List<String> listId, Context context) {
        String s = "";
        for (int i = 0; i < listId.size(); i++) {
            s = s + listId.get(i) + "|";
        }
        setStringPref(KEY_LIS_ID, s, context);
    }

    //==============================================================================================
    // Universal shared preference
    // for string
    //
    //==============================================================================================

    private static String getStringPref(String key_val, String def_val, Context context) {
        SharedPreferences pref = context.getSharedPreferences("pref_" + key_val, context.MODE_PRIVATE);
        return pref.getString(key_val, def_val);
    }

    private static void setStringPref(String key_val, String val, Context context) {
        SharedPreferences pref = context.getSharedPreferences("pref_" + key_val, context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.clear();
        prefEditor.putString(key_val, val);
        prefEditor.commit();
    }

    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public static void addFavorite(Context context, Publicacoes publicacoes) {
        List<Publicacoes> favorites = getFavorites(context);
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        favorites.add(publicacoes);
        saveFavorites(context, favorites);
    }

    public static void removeFavorite(Context context, Publicacoes publicacoes) {
        ArrayList<Publicacoes> favorites = getFavorites(context);
        if (favorites != null) {

            String idNew = publicacoes.getId();

            for (int i = 0; i < favorites.size(); i++) {
                Publicacoes f = favorites.get(i);
                if (f.getId().equals(idNew)) {
                    favorites.remove(i);
                }
            }

            saveFavorites(context, favorites);
        }
    }

    // GetList
    //==============================================================================================

    public static ArrayList<Publicacoes> getFavorites(Context context) {
        SharedPreferences settings;
        List<Publicacoes> favorites;
        if (context != null) {
            settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            if (settings.contains(FAVORITES)) {
                String jsonFavorites = settings.getString(FAVORITES, null);
                Gson gson = new Gson();
                Publicacoes[] favoriteItems = gson.fromJson(jsonFavorites, Publicacoes[].class);
                favorites = Arrays.asList(favoriteItems);
                favorites = new ArrayList<>(favorites);
            } else {
                return null;
            }

            return (ArrayList<Publicacoes>) favorites;
        }
        return null;
    }

    // SaveList
    //==============================================================================================

    // This four methods are used for maintaining favorites.
    public static void saveFavorites(Context context, List<Publicacoes> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(FAVORITES, jsonFavorites);
        editor.commit();
    }

    // verify existes
    //==============================================================================================
    public static boolean isPublicacoesExist(Context context, Publicacoes publicacoes) {
        ArrayList<Publicacoes> favorites = getFavorites(context);

        String idNew = publicacoes.getId();

        if (favorites != null) {
            for (int i = 0; i < favorites.size(); i++) {
                Publicacoes f = favorites.get(i);
                if (f.getId().equals(idNew)) {
                    return true;
                }
            }
        }
        return false;
    }


}
