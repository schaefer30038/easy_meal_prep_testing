//package com.example.easymealprep;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////import android.os.Bundle;
////
////public class CreateNewAccount extends AppCompatActivity {
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_create_new_account);
////    }
////}


package com.example.easymealprep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easymealprep.MainActivity;
import com.example.easymealprep.R;

public class CreateNewAccount extends AppCompatActivity {
    TextView newAccountLabel;
    EditText username, password, name, email;
    Button createAccount;


//    private static final int PICK_IMAGE = 100;
//    Uri imageUri;
//    ImageView imageView;
//    Button Upload_Button;
//    Button createacc;
//    EditText newAccountUsername_PlainText, newAccountPassword_PlainText,
//            newAccountAddress_PlainText, newAccountPhone_PlainText,newAccountEmail_PlainText;
//    static String accMade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newAccountLabel = (TextView) findViewById(R.id.newAccountLabel_TextView);

        username = (EditText) findViewById(R.id.username_EditText);
        password = (EditText) findViewById(R.id.password_EditText);
        name = (EditText) findViewById(R.id.name_EditText);
        email = (EditText) findViewById(R.id.email_EditText);

        createAccount = (Button) findViewById(R.id.createAccount_Button);

        //createAccount.setOnClickListener(this);


//        setContentView(R.layout.activity_create_new_account);
//        AlertDialog.Builder b = new AlertDialog.Builder(CreateNewAccount.this);
//
//        newAccountUsername_PlainText = (EditText) findViewById(R.id.newAccountUsername_PlainText);
//        newAccountPassword_PlainText = (EditText) findViewById(R.id.newAccountPassword_PlainText);
//        newAccountAddress_PlainText = (EditText) findViewById(R.id.newAccountAddress_PlainText);
//        newAccountPhone_PlainText = (EditText) findViewById(R.id.newAccountPhone_PlainText);
//        newAccountEmail_PlainText = (EditText) findViewById(R.id.newAccountEmail_PlainText);
//
//        imageView = (ImageView) findViewById(R.id.IDProf);
//        imageView.setImageResource(R.drawable.bee1);
//        Upload_Button = (Button) findViewById(R.id.upload_Button);
//        Upload_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });
//        createacc = findViewById(R.id.createNewAccount_Button);
//        createacc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Send user data to backend
//                hideKeyboard(CreateNewAccount.this);
//                sendData();
//            }
//        });
    }

//    @Override
//    public void onClick(View v) {
//        switch(v.getId()) {
//            case R.id.createAccount_Button:  // TODO do the actions
//                break;
//        }
//    }

//    private void sendData() {
//        String username = newAccountUsername_PlainText.getText().toString();
//        AlertDialog.Builder b = new AlertDialog.Builder(CreateNewAccount.this);
//        String pass = newAccountPassword_PlainText.getText().toString();
//        String adr = newAccountAddress_PlainText.getText().toString();
//        String phone = newAccountPhone_PlainText.getText().toString();
//        String email = newAccountEmail_PlainText.getText().toString();
//        if(!(username.length() == 0 || pass.length() == 0 || adr.length() == 0 || phone.length() == 0 || email.length() == 0 )){
//            //Send data to back end with photo
//            new CreateAccountAsync().execute(username, encoder(pass), null, adr, email, phone);
//
//            // TODO: accMade will be "Success" if account is created and it will be "Exist" if account already exists
//            //if(accMade.equals("Success")) {
//
//            while(!MainActivity.done){
//                if(MainActivity.done){
//                    MainActivity.done = false;
//                    break;
//                }
//            }
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run(){
//                    System.out.print(accMade);
//                    if(accMade.equals("Success")){
//                        Intent intent2Main = new Intent(CreateNewAccount.this, MainActivity.class);
//                        startActivity(intent2Main);
//                    }
//                    else{
//                        // Show error
//                        Toast.makeText(CreateNewAccount.this,"Account already exists",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            },1000);
//
//
//
//
//        }
//
//    }

//    private void openGallery() {
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, PICK_IMAGE);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
//            imageUri = data.getData();
//            imageView.setImageURI(imageUri);
//        }
//    }
    // encodes the password to different string
//    public static String encoder(String input) {
//        String result = "";
//        for (char ch: input.toCharArray()) {
//            int temp = ch + 6;
//            if (temp > 127) {
//                temp = temp - 127;
//            }
//            result = result + ((char) temp);
//        }
//        return result;
//    }

    // decodes the encoded password to original string
//    public static String decoder(String input) {
//        String result = "";
//        for (char ch: input.toCharArray()) {
//            int temp = ch - 6;
//            if (temp < 0) {
//                temp = 127 + temp;
//            }
//            result = result + ((char) temp);
//        }
//        return result;
//    }
//    public static void hideKeyboard(Activity activity) {
//        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        //Find the currently focused view, so we can grab the correct window token from it.
//        View view = activity.getCurrentFocus();
//        //If no view currently has focus, create a new one, just so we can grab a window token from it
//        if (view == null) {
//            view = new View(activity);
//        }
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
}