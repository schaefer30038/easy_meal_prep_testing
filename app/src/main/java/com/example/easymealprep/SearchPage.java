package com.example.easymealprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import static com.example.easymealprep.Statics.check;

public class SearchPage extends AppCompatActivity implements View.OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener{
    TextView title;
    TextView searchbox;
    Button search;
    BottomNavigationView nav;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);

        title = (TextView) findViewById(R.id.titleSearch);
        search = (Button) findViewById(R.id.search);
        searchbox = (TextView)findViewById(R.id.searchField);
        nav = (BottomNavigationView) findViewById(R.id.bottomNavSearch);
        nav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
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
        } else if (id == R.id.nav_add_recipe) {
            fragment = new AddRecipeFragment();
        } else if (id == R.id.nav_favorites) {
            fragment = new FavoritesFragment();
        } else if (id == R.id.nav_setting) {
            fragment = new SettingFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        return true;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search:
                String data = (String) searchbox.getText();
                sendData(data);
                break;

        }
    }
    //TODO for search
    private void sendData(String data) {
        //data is recipie title
    }

}
