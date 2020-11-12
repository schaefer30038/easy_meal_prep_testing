package com.example.easymealprep;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecipeFragment} factory method to
 * create an instance of this fragment.
 */
public class AddRecipeFragment extends Fragment {
    private static final int PICK_IMAGE = 100;
    EditText enterFoodName, enterFoodDescription, enterFoodIngredient, enterFoodTools;
    Button button, createRecipe_button;
    ImageView imageView;
    Uri imageUri;

    public AddRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inputFragmentView = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        // Inflate the layout for this fragment

        enterFoodName = (EditText) inputFragmentView.findViewById(R.id.enterFoodName);
        enterFoodDescription = (EditText) inputFragmentView.findViewById(R.id.enterFoodDescription);
        enterFoodIngredient = (EditText) inputFragmentView.findViewById(R.id.enterFoodIngredient);
        enterFoodTools = (EditText) inputFragmentView.findViewById(R.id.enterFoodTools);

        imageView = (ImageView) inputFragmentView.findViewById(R.id.imageView);

        button = (Button) inputFragmentView.findViewById(R.id.button);
        createRecipe_button = (Button) inputFragmentView.findViewById(R.id.createRecipe_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Made it to AddRecipeFragment, onClick, button");

                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Made it to AddRecipeFragment, onClick, button, first if statement");

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2000);
                }
                else {
                    System.out.println("Made it to AddRecipeFragment, onClick, button, else statement");
                    startGallery();
                }
            }
        });

        createRecipe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Made it to AddRecipeFragment, onClick, createRecipe_button");
                String name = enterFoodName.getText().toString();
                String description = enterFoodDescription.getText().toString();
                String ingredient = enterFoodIngredient.getText().toString();
                String tool = enterFoodTools.getText().toString();
                sendData(name, description, ingredient, tool, imageUri);
            }
        });


        return inputFragmentView;
    }

    private void sendData(String name, String description, String ingredient, String tool, Uri imageUri) {

    }

    private void startGallery() {
        System.out.println("Made it to AddRecipeFragment, startGallery");

        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Made it to AddRecipeFragment, onActivityResult");
        if (resultCode == -1 && requestCode == 1000){
            System.out.println("Made it to AddRecipeFragment, onActivityResult, if statement");
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}