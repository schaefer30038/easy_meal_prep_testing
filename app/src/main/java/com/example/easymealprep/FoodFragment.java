package com.example.easymealprep;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFragment#} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {

    TextView foodNameTV, foodDescTV, ingredientsTV, instructionsTV;
    ImageView foodPicIV;
    String instruction = "";

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inputFragmentView = inflater.inflate(R.layout.fragment_food, container, false);
        // Inflate the layout for this fragment
        foodNameTV = (TextView) inputFragmentView.findViewById(R.id.food_name_tv);
        foodDescTV = (TextView) inputFragmentView.findViewById(R.id.food_desc_tv);
        foodPicIV = (ImageView) inputFragmentView.findViewById(R.id.food_pic_iv);
        // TODO ingredient list when implements
        ingredientsTV = (TextView) inputFragmentView.findViewById(R.id.ingredients_tv);
        instructionsTV = (TextView) inputFragmentView.findViewById(R.id.instructions_tv);
        int foodID = (int) Statics.currFood[0];
        String foodName = (String) Statics.currFood[1];
        String foodDesc = (String) Statics.currFood[2];
        byte[] foodPic = (byte[]) Statics.currFood[3];
        foodNameTV.setText(foodName);
        foodDescTV.setText(foodDesc);

        if (foodPic == null)
            foodPicIV.setImageResource(R.drawable.default_food_pic);
        else {
            String s = null;
            try {
                s = new String(foodPic, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.parse(s);
            foodPicIV.setImageURI(uri);
        }
        sendData(foodID);
        return inputFragmentView;
    }

    private void sendData(int foodID) {
        new SearchOneRecipeAsync().execute(foodID);
    }

    public class SearchOneRecipeAsync extends AsyncTask<Integer,Void,Void> {
        Recipe recipe;
        ResultSet resultSet;
        @Override
        protected Void doInBackground(Integer... integers) {
            recipe = new Recipe(Statics.connection.getConnection(), Statics.currUserAccount);
            int foodID = integers[0];
            System.out.println(foodID);
            resultSet = recipe.searchOneRecipe(foodID);
            System.out.println("recipe back");
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
            if (resultSet != null) {
                System.out.println("recipe post");
                try {
                    while (resultSet.next()) {
                        int step = resultSet.getInt("step");
                        String inst = resultSet.getString("instruction");
                        instruction = instruction + step + ". " + inst + "\n";
                    }
                } catch (SQLException e) {
                    System.out.println("recipe error post");
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("resultset null");
            }
            System.out.println(instruction);

            instructionsTV.setText(instruction);
        }
    }
}