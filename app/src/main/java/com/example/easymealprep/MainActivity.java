package com.example.easymealprep;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.os.Handler;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.easymealprep.R;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView title, loginScreenLabel, login;
    EditText username, password;
    Button newAccount;


    //ImageView uploadPicture_ImageView;
    private ProgressBar prog;
    // TODO: this string will be "Username" if account doesn't exist
    // it will be "Password" if the password is wrong and    if username and password is a match it will return "Match"
    //static String login;
    // Stores the current username
    //static String currUser;

    //static boolean done = false;
    //public static final int GET_FROM_GALLERY = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        title = (TextView) findViewById(R.id.title_TextView);
        loginScreenLabel = (TextView) findViewById(R.id.loginScreenLabel_TextView);
        login = (TextView) findViewById(R.id.login_TextView);

        username = (EditText) findViewById(R.id.username_EditText);
        password = (EditText) findViewById(R.id.password_EditText);

        newAccount = (Button) findViewById(R.id.newAccount_Button);

        newAccount.setOnClickListener(this);




        //Login_Button = (Button) findViewById(R.id.Login_Button);
        //CreateNewAccount_Button = (Button) findViewById(R.id.CreateNewAccount_Button);
        //prog = (ProgressBar)findViewById(R.id.progressBar) ;
        //prog.setVisibility(View.GONE);




        //LogicUsername_PlainText = (EditText) findViewById(R.id.LogicUsername_PlainText);
        //LoginPassword_PlainText = (EditText) findViewById(R.id.LoginPassword_PlainText);


        //Login_Button.setOnClickListener(this);
        //CreateNewAccount_Button.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.newAccount_Button:
                Intent intent2CreateNewAccountPage = new Intent(MainActivity.this, CreateNewAccount.class);
                startActivity(intent2CreateNewAccountPage);
                break;

            //case R.id.Login_Button:
            //    prog.setVisibility(View.VISIBLE);
            //    sendData();
            //    break;
            //case R.id.CreateNewAccount_Button:
             //   Intent intent2CreateNewAccountPage = new Intent(MainActivity.this, CreateNewAccount.class);
             //   startActivity(intent2CreateNewAccountPage);
             //   break;
        }
    }

    private void sendData() {
//        hideKeyboard(MainActivity.this);
//        currUser = LogicUsername_PlainText.getText().toString();
//        String pass = LoginPassword_PlainText.getText().toString();
//        new SearchAccountAsync().execute(currUser, CreateNewAccount.encoder(pass));
//
//        //TODO check value of LOGIN
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run(){
//                SearchAccountAsync.connect.closeConnection();
//                if(login.equals("Match")){
//                    Intent intent2Main = new Intent(MainActivity.this, MainActivity2.class);
//                    startActivity(intent2Main);
//                }
//                else if(login.equals("Username")){
//                    prog.setVisibility(View.GONE);
//                    Toast.makeText(MainActivity.this,"Account doesn't exist",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    prog.setVisibility(View.GONE);
//                    // Show error
//                    Toast.makeText(MainActivity.this,"Incorrect Login Credentials",Toast.LENGTH_SHORT).show();
//                }
//                prog.setVisibility(View.GONE);
//            }
//        },3000);

    }
    public static void hideKeyboard(Activity activity) {
//        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        //Find the currently focused view, so we can grab the correct window token from it.
//        View view = activity.getCurrentFocus();
//        //If no view currently has focus, create a new one, just so we can grab a window token from it
//        if (view == null) {
//            view = new View(activity);
//        }
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}