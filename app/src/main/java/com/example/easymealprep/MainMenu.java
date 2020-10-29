package com.example.easymealprep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button quit;
    NavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        quit = (Button) findViewById(R.id.Quit);
            nav = (NavigationView) findViewById(R.id.bottomNav);
        nav.setNavigationItemSelectedListener(this);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2login = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent2login);
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_favorites) {



        }
        return true;
    }


}