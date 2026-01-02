package com.example.testproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavoritesManager {

    private static FavoritesManager instance;

    // Store favorites per user
    private final HashMap<String, ArrayList<Painting>> favoritesPerUser = new HashMap<>();

    private FavoritesManager() {
        // Private constructor for singleton
    }

    public static FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    // Add a painting to a specific user's favorites
    public void addFavorite(String username, Painting painting) {
        if (username == null || painting == null) return;

        ArrayList<Painting> userFavorites = favoritesPerUser.getOrDefault(username, new ArrayList<>());

        if (!userFavorites.contains(painting)) {
            userFavorites.add(painting);
            painting.setFavorite(true); // mark painting as favorite
            favoritesPerUser.put(username, userFavorites);
        }
    }

    // Remove a painting from a specific user's favorites
    public void removeFavorite(String username, Painting painting) {
        if (username == null || painting == null) return;

        ArrayList<Painting> userFavorites = favoritesPerUser.get(username);
        if (userFavorites != null && userFavorites.remove(painting)) {
            painting.setFavorite(false); // mark painting as not favorite
        }
    }

    // Retrieve favorites for a specific user
    public List<Painting> getFavorites(String username) {
        if (username == null) return new ArrayList<>();
        return new ArrayList<>(favoritesPerUser.getOrDefault(username, new ArrayList<>()));
    }

    // Check if a painting is in favorites for a user
    public boolean isFavorite(String username, Painting painting) {
        if (username == null || painting == null) return false;
        ArrayList<Painting> userFavorites = favoritesPerUser.get(username);
        return userFavorites != null && userFavorites.contains(painting);
    }
}
