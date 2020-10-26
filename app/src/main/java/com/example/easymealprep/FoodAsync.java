package com.example.easymealprep;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class FoodAsync {
    Food food = new Food(Statics.connection.getConnection(), AccountAsync.account.getUserAccount());

    public class CreateFoodAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String foodName = strings[0];
            String foodDescription = strings[1];
            String picture = strings[2];
            boolean bool = food.createFood(foodName, foodDescription, picture);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class DeleteFoodAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String foodName = strings[0];
            boolean bool = food.deleteFood(foodName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class SearchFoodAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String foodName = strings[0];
            ResultSet resultSet = food.searchFood(foodName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class UpdateFoodAsync extends AsyncTask<Object,Void,Void> {
        @Override
        protected Void doInBackground(Object... objects) {
            int foodID = objects[0];
            String foodName = objects[1];
            String foodDescription = objects[2];
            String picture = objects[3];
            boolean bool = food.updateFood(foodID, foodName, foodDescription, picture));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class ListAllFoodAsync extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            ResultSet resultSet = food.listAllFood();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class ListUserFoodAsync extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            ResultSet resultSet = food.listUserFood();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }
}
