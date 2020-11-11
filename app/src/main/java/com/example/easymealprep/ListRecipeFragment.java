package com.example.easymealprep;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ListRecipeFragment extends Fragment {
    ListView listView;
    ArrayList <Object[]> arrayLists = new ArrayList <Object[]>();
    // TODO add titles for recipes to list
    // TODO connect to FoodFragment to view recipe

    public ListRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inputFragmentView = inflater.inflate(R.layout.fragment_list, container, false);
        // Inflate the layout for this fragment
       // System.out.println("home fragment");
       // quit_button = (Button) inputFragmentView.findViewById(R.id.Quit);
        ArrayList<String> list = new ArrayList<>();
        listView = (ListView) inputFragmentView.findViewById(R.id.recipeTitles);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);





        return inputFragmentView;
    }
}