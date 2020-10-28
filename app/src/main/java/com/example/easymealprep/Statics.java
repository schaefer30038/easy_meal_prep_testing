package com.example.easymealprep;

public class Statics {
    static SQLConnection connection = new SQLConnection();
    static boolean loop = true;
    static boolean check = false;
    static String currUserAccount,currUserEmail,currPassword;
    static SQLConnect connect;
    static AccountAsync async = new AccountAsync();;
}
