package com.example.testproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Painting> paintingArrayList;
    private static MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ------------------- TOOLBAR -------------------
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // --- Get username from NameActivity ---
        String userName = getIntent().getStringExtra("username");
        if (userName != null) {
            Toast.makeText(
                    MainActivity.this,
                    "Welcome " + userName + " to the world of paintings!",
                    Toast.LENGTH_LONG
            ).show();
        }

        // ------------------- LISTVIEW SETUP -------------------
        listView = findViewById(R.id.listview);
        // --- Initialize database ---
        AppDatabase db = AppDatabase.getInstance(this);
        PaintingDao dao = db.paintingDao();

// --- Insert default paintings if DB is empty ---
        if (dao.getAllPaintings().isEmpty()) {
            dao.insert(new Painting("Mona Lisa", "Leonardo da Vinci", R.drawable.mona_lisa));
            dao.insert(new Painting("The Birth of Venus", "Sandro Botticelli", R.drawable.birth_of_vinus));
            dao.insert(new Painting("The Scream", "Evard Munch", R.drawable.scream));
            dao.insert(new Painting("American Gothic", "Grant Wood", R.drawable.american_gothic));
            dao.insert(new Painting("The Arnolfini", "Jan van Eyck", R.drawable.arnolfini_portrait));
            dao.insert(new Painting("Whistler’s Mother", "James McNeill Whistler", R.drawable.whistlers_mother));
            dao.insert(new Painting("Girl with a Pearl", "Johannes Vermeer", R.drawable.girl_with_pearl));
        }

// --- Load paintings from database ---
        paintingArrayList = new ArrayList<>(dao.getAllPaintings());

// --- Set up adapter ---
        adapter = new MyCustomAdapter(getApplicationContext(), paintingArrayList, userName);
        listView.setAdapter(adapter);

        // ------------------- EDGE-TO-EDGE PADDING -------------------
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                }
        );
    }

    // ------------------- MENU INFLATION -------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate your menu resource (res/menu/tabs.xml)
        getMenuInflater().inflate(R.menu.tabs, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_favorites) { // Make sure your menu item id is correct
            // Open Favorites fragment
            FavFragment favFragment = new FavFragment(getIntent().getStringExtra("username"));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main, favFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
