package com.example.easymealprep;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    TextView title;
    EditText searchbox;
    Button search_button;
    ListView list;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inputFragmentView = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        title = (TextView) inputFragmentView.findViewById(R.id.titleSearch);
        searchbox = (EditText) inputFragmentView.findViewById(R.id.searchField);
        search_button = (Button) inputFragmentView.findViewById(R.id.search_b);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = (String) searchbox.getText().toString();
                sendData(data);
            }
        });
        return inputFragmentView;
    }
    //TODO for search
    private void sendData(String data) {
        //data is recipie title
        System.out.println(data);
    }
}