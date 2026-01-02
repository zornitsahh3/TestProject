package com.example.testproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {

    private ListView favoritesListView;
    private MyCustomAdapter adapter;
    private final String username;
    private Button btnBack;

    public FavFragment(String username) {
        this.username = username;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        favoritesListView = view.findViewById(R.id.favoritesListView);
        btnBack = view.findViewById(R.id.btnBackToPaintings);

        // Load favorites for this user
        List<Painting> allFavorites = FavoritesManager.getInstance().getFavorites(username);

        // Initialize adapter
        adapter = new MyCustomAdapter(getContext(), new ArrayList<>(allFavorites), username);
        favoritesListView.setAdapter(adapter);

        // Back button to return to main paintings list
        btnBack.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh favorites whenever fragment is visible
        List<Painting> allFavorites = FavoritesManager.getInstance().getFavorites(username);
        adapter.clear();
        adapter.addAll(allFavorites);
        adapter.notifyDataSetChanged();
    }
}
