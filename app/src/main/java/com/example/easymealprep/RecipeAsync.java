package com.example.easymealprep;

import android.os.AsyncTask;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RecipeAsync {
    Recipe recipe;
    public class CreateRecipeAsync extends AsyncTask<Object,Void,Void> {
        @Override
        protected Void doInBackground(Object... obj) {
            recipe = new Recipe(Statics.connection.getConnection(), Statics.currUserAccount);
            int foodID = (int) obj[0];
            ArrayList<String> instruction = (ArrayList) obj[1];
            Statics.check = recipe.createRecipe(foodID, instruction);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }
    public class DeleteRecipeAsync extends AsyncTask<Integer,Void,Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            recipe = new Recipe(Statics.connection.getConnection(), Statics.currUserAccount);
            int foodID = integers[0];
            Statics.check = recipe.deleteRecipe(foodID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }
    public class UpdateRecipeAsync extends AsyncTask<Object,Void,Void> {
        @Override
        protected Void doInBackground(Object... obj) {
            recipe = new Recipe(Statics.connection.getConnection(), Statics.currUserAccount);
            int foodID = (int) obj[0];
            ArrayList<String> instruction = (ArrayList) obj[1];
            Statics.check = recipe.updateRecipe(foodID, instruction);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }
    public class SearchOneRecipeAsync extends AsyncTask<Integer,Void,Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            recipe = new Recipe(Statics.connection.getConnection(), Statics.currUserAccount);
            int foodID = integers[0];
            ResultSet resultSet = recipe.searchOneRecipe(foodID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }
}
