package com.example.easymealprep;

import android.os.AsyncTask;
import java.sql.ResultSet;

public class AccountAsync {
    static Account account = new Account(Statics.connection.getConnection());

    public class LoginAccountAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String accountName = strings[0];
            String password = strings[1];
            Statics.login = account.loginAccount(accountName,password);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class CreateAccountAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String accountName = strings[0];
            String userPassword = strings[1];
            String userName = strings[2];
            String userEmail = strings[3];
            Statics.login = account.createAccount(accountName, userPassword, userName, userEmail);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class DeleteAccountAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String userPassword = strings[0];
            Statics.login = account.deleteAccount(userPassword);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class UpdateAccountAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String userPassword = strings[0];
            String userName = strings[1];
            String userEmail = strings[2];
            Statics.login = account.updateAccount(userPassword, userName, userEmail);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class GetFavoriteAsync extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            ResultSet resultSet = account.getFavorite();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class SetFavoriteAsync extends AsyncTask<Integer,Void,Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            int foodID = integers[0];
            Statics.login = account.setFavorite(foodID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }

    public class DeleteFavoriteAsync extends AsyncTask<Integer,Void,Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            int foodID = integers[0];
            Statics.login = account.deleteFavorite(foodID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }
}
