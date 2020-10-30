package com.example.easymealprep;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    TextView title;
    EditText searchBox;
    Button search_button;
    ListView listView;
    ArrayList <Object[]> arrayLists = new ArrayList <Object[]>();


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inputFragmentView = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        title = (TextView) inputFragmentView.findViewById(R.id.titleSearch);
        searchBox = (EditText) inputFragmentView.findViewById(R.id.searchField);
        listView = (ListView) inputFragmentView.findViewById(R.id.list);
        search_button = (Button) inputFragmentView.findViewById(R.id.search_b);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = (String) searchBox.getText().toString();
                sendData(data);
            }
        });
        return inputFragmentView;
    }

    //TODO for search
    private void sendData(String data) {
        //data is recipe title
        System.out.println(data);
        new SearchFoodAsync().execute(data);
    }

    public class SearchFoodAsync extends AsyncTask<String, Void, ResultSet> {
        Food food;

        @Override
        protected ResultSet doInBackground(String... strings) {
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
            String foodName = strings[0];
            ResultSet resultSet = food.searchFood(foodName);
            return resultSet;
        }

        @Override
        protected void onPostExecute(ResultSet resultSet) {
            super.onPostExecute(resultSet);
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
                }
            });
        }
    }
}