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
    public class ListAllFoodAsync extends AsyncTask<Void,Void,Void> {
        Food food;
        ResultSet resultSet;
        @Override
        protected Void doInBackground(Void... voids) {
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
            resultSet = food.listAllFood();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> list = new ArrayList<>();
            if (resultSet != null) {
                System.out.println("ASDasd");
                try {
                    while (resultSet.next()) {
                        int foodID = resultSet.getInt("foodID");
                        String foodName = resultSet.getString("foodName");
                        String foodDescription = resultSet.getString("foodDescription");
                        list.add(foodName);
                        byte [] foodPic = resultSet.getBytes("foodPic");
                        Object[] array = new Object[4];
                        array[0] = foodID;
                        array[1] = foodName;
                        array[2] = foodDescription;
                        array[3] = foodPic;
                        arrayLists.add(array);
                        System.out.println(foodName);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Statics.currFood[0] = arrayLists.get(position)[0];
                    Statics.currFood[1] = arrayLists.get(position)[1];
                    Statics.currFood[2] = arrayLists.get(position)[2];
                    Statics.currFood[3] = arrayLists.get(position)[3];
                    Fragment newFragment = new FoodFragment();
                    // consider using Java coding conventions (upper first char class names!!!)
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
            });
        }
    }
}