package com.example.easymealprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
// MOST OF THIS FILE WAS CREATED IN ITERATION 1
public class MainMenu extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Button quit;
    private BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
//        quit = (Button) findViewById(R.id.Quit);
        nav = (BottomNavigationView) findViewById(R.id.bottomNav);
        nav.setOnNavigationItemSelectedListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


//        quit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Statics.connection.closeConnection();
//                Intent intent2login = new Intent(MainMenu.this, MainActivity.class);
//                startActivity(intent2login);
//            }
//        });

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_search) {
            fragment = new SearchFragment();
        } else if (id == R.id.nav_add_recipe) { // THIS WHOLE LiNE WAS CREATED IN ITERATION 2
            fragment = new AddRecipeFragment();// THIS WHOLE LiNE WAS CREATED IN ITERATION 2
        } else if (id == R.id.nav_favorites) {
            fragment = new FavoritesFragment();
        } else if (id == R.id.nav_setting) {
            fragment = new SettingFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        return true;
    }


}