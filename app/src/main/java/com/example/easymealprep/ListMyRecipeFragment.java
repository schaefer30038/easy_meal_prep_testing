package com.example.easymealprep;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


public class ListMyRecipeFragment extends Fragment {
    private View view;
    private ListView mListview;
    private ArrayList<String> mArrData;
    private ListAdapter mAdapter;
    // TODO add titles for recipes to list
    // TODO connect to FoodFragment to view recipe

    public ListMyRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View inputFragmentView = inflater.inflate(R.layout.fragment_list, container, false);
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_list, container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        mListview = (ListView) view.findViewById(R.id.recipeTitles);
        mArrData = new ArrayList<String>();
        mAdapter = new ListAdapter(getActivity(),R.layout.customlistlayout, mArrData);
        mListview.setAdapter(mAdapter);
        mArrData.add("fuck");// TODO add to list here
        mArrData.add("fuck");
        mArrData.add("fuck");
        mArrData.add("fuck");
        new ListAllFoodAsync().execute();
        return view;
    }
    public class ListAllFoodAsync extends AsyncTask<Void,Void,Void> {
        Food food;
        ResultSet resultSet;
        @Override
        protected Void doInBackground(Void... voids) {
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
            resultSet = food.listAllFood();
            return null;
        }
        // TODO get only my recipe
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}