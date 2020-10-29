package com.example.easymealprep;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class FoodAsync {
    Food food;

    public class CreateFoodAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
            String foodName = strings[0];
            String foodDescription = strings[1];
            String picture = strings[2];
            Statics.check = food.createFood(foodName, foodDescription, picture);
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
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
            String foodName = strings[0];
            Statics.check = food.deleteFood(foodName);
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
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
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
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
            int foodID = (int) objects[0];
            String foodName = (String) objects[1];
            String foodDescription = (String) objects[2];
            String picture = (String) objects[3];
            Statics.check = food.updateFood(foodID, foodName, foodDescription, picture);
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
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
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
            food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
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
