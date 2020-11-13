package com.example.easymealprep;

import android.os.AsyncTask;
import java.sql.ResultSet;
//
public class AccountAsync {
    Account account;

    public class LoginAccountAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            System.out.println("Entered in LoginAccountAsync:doInBackground ");
            String accountName = strings[0];
            String password = strings[1];
            account = new Account(Statics.connection.getConnection());
            Statics.check = account.loginAccount(accountName,password);
//            return null;
//            if (Statics.connect == null)
//                System.out.println("new connect");
//                Statics.connect = new SQLConnect();
//            String username = strings[0];
//            String password = strings[1];
//            System.out.println("login start");
//            Statics.check = false;
//            Statics.check = Statics.connect.loginAccount("Admin", "Administrator");
////            Statics.check = connect.loginAccount(username, password);
////            Statics.loop = true;
            System.out.println(Statics.check + "do in bac");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            System.out.println("onPost exec1");
            super.onPostExecute(aVoid);
            System.out.println("onPost exec2");
            Statics.loop = false;
//            Statics.connect.closeConnection();
            System.out.println("onPost exec3");
        }
    }

    public class CreateAccountAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
//            conn = new SQLConnect();
//            String accountName = strings[0];
//            String userPassword = strings[1];
//            String userName = strings[2];
//            String userEmail = strings[3];
//            System.out.println("create async");
////            Statics.check = Statics.connect.createAccount(accountName, userPassword, userName, userEmail);
//            Statics.check = conn.createAccount("tsewang", "tsewang", "tsewang", "tsewang");
//            return null;
            account = new Account(Statics.connection.getConnection());
            String accountName = strings[0];
            String userPassword = strings[1];
            String userName = strings[2];
            String userEmail = strings[3];
            System.out.println("create async");
//            Statics.check = account.createAccount("tsewang2", "tsewang2", "tsewang2", "tsewang2");
            Statics.check = account.createAccount(accountName, userPassword, userName, userEmail);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            System.out.println("onPost exec");
            Statics.loop = false;
//            conn.closeConnection();
        }
    }

    public class DeleteAccountAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String userPassword = strings[0];
            account = new Account(Statics.connection.getConnection());
            Statics.check = account.deleteAccount(userPassword);
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
            account = new Account(Statics.connection.getConnection());
            Statics.check = account.updateAccount(userPassword, userName, userEmail);
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
            account = new Account(Statics.connection.getConnection());
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
            account = new Account(Statics.connection.getConnection());
            Statics.check = account.setFavorite(foodID);
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
            account = new Account(Statics.connection.getConnection());
            Statics.check = account.deleteFavorite(foodID);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Statics.loop = false;
        }
    }
}
