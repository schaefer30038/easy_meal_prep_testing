package com.example.easymealprep;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView title, loginScreenLabel, login;
    EditText username, password;
    Button newAccount, loginB;
    static boolean loginCheck;


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
        loginB = (Button) findViewById(R.id.loginB_Button);

        newAccount.setOnClickListener(this);
        loginB.setOnClickListener(this);

        //prog = (ProgressBar)findViewById(R.id.progressBar) ;
        //prog.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        username.setText("");
        password.setText("");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.newAccount_Button:
                System.out.println("Made it in switch statement newAccountButton");
                Intent intent2CreateNewAccountPage = new Intent(MainActivity.this, CreateNewAccount.class);
                startActivity(intent2CreateNewAccountPage);
                break;

            case R.id.loginB_Button:
                System.out.println("Made it in switch statement loginB");
//                prog.setVisibility(View.VISIBLE);
                sendData();
                break;
        }
    }

    private void sendData() {
        hideKeyboard(MainActivity.this);
        String currUser = username.getText().toString();
        String pass = password.getText().toString();
        System.out.println("Testing BEFRORE LoginAccountAsync");
        // FIXME CRASHING RIGHT HERE 
        AccountAsync async = new AccountAsync();
        async.new LoginAccountAsync().execute(currUser, pass);
        System.out.println("Testing AFTER LoginAccountAsync");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                System.out.println("Inside sendData handler, Run method");
                if(loginCheck){
                    System.out.println("loginCheck works");
                    Intent intent2Main = new Intent(MainActivity.this, CreateNewAccount.class);
                    startActivity(intent2Main);
                }
                else{
                    System.out.println("loginCheck didnt work");

//            prog.setVisibility(View.GONE);
                    // Show error
                    Toast.makeText(MainActivity.this,"Incorrect Login Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);
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