package com.example.easymealprep;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecipeFragment} factory method to
 * create an instance of this fragment.
 */
public class EditRecipeFragment extends Fragment {
    private static final int PICK_IMAGE = 100;
    EditText enterFoodName, enterFoodDescription, enterSteps, enterFoodIngredient, enterFoodTools;
    Button button, updateRecipe_button;
    ImageView imageView;
    Uri imageUri;
    Bitmap bitmap;

    public EditRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inputFragmentView = inflater.inflate(R.layout.fragment_edit_recipe, container, false);
        // Inflate the layout for this fragment

        enterFoodName = (EditText) inputFragmentView.findViewById(R.id.enterFoodName);
        enterFoodDescription = (EditText) inputFragmentView.findViewById(R.id.enterFoodDescription);
        enterSteps = (EditText) inputFragmentView.findViewById(R.id.enterSteps);
        enterFoodIngredient = (EditText) inputFragmentView.findViewById(R.id.enterFoodIngredient);
        enterFoodTools = (EditText) inputFragmentView.findViewById(R.id.enterFoodTools);

        imageView = (ImageView) inputFragmentView.findViewById(R.id.imageView);

        button = (Button) inputFragmentView.findViewById(R.id.button);
        updateRecipe_button = (Button) inputFragmentView.findViewById(R.id.createRecipe_button);
        String foodName = (String) Statics.currFood[1];
        String foodDesc = (String) Statics.currFood[2];
        byte[] foodPic = (byte[]) Statics.currFood[3];
        enterFoodName.setText(foodName);
        enterFoodDescription.setText(foodDesc);

        if (foodPic != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(foodPic, 0, foodPic.length);
            imageView.setImageBitmap(bmp);
        }
        new GetRecipeInfoAsync().execute();
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

        updateRecipe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Made it to AddRecipeFragment, onClick, createRecipe_button");
                String name = enterFoodName.getText().toString();
                String description = enterFoodDescription.getText().toString();
                String instruction = enterSteps.getText().toString();
                String ingredient = enterFoodIngredient.getText().toString();
                String tool = enterFoodTools.getText().toString();
                try {
                    sendData(name, description, instruction, ingredient, tool, bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        return inputFragmentView;
    }

    private void sendData(String name, String description, String instruction, String ingredient, String tool, Bitmap bitmap) throws IOException {
        byte[] imageByte = null;
        if(bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap = getResizedBitmap(bitmap, 1000);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream);

            imageByte = stream.toByteArray();
        }
        new UpdateRecipeAsync().execute(name, description, imageByte, instruction, ingredient, tool);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        Bitmap result = image;
        int width = image.getWidth();
        int height = image.getHeight();
        if (maxSize < width || maxSize < height) {
            System.out.println("resize bitmap");
            float bitmapRatio = (float) width / (float) height;
            if (bitmapRatio > 1) {
                width = maxSize;
                height = (int) (width / bitmapRatio);
            } else {
                height = maxSize;
                width = (int) (height * bitmapRatio);
            }
            result = Bitmap.createScaledBitmap(image, width, height, true);
        }
        System.out.println("no resize bitmap");

        return result;
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
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class GetRecipeInfoAsync extends AsyncTask<Void,Void,Void> {
        ResultSet recipeResultSet;
        ArrayList [] ingredientsList;
        ArrayList [] toolsList;
        @Override
        protected Void doInBackground(Void... voids) {
            int foodID = (int) Statics.currFood[0];
            Recipe recipe = new Recipe(Statics.connection.getConnection(), Statics.currUserAccount);
            recipeResultSet = recipe.searchOneRecipe(foodID);
            Ingredient ingredient = new Ingredient(Statics.connection.getConnection());
            ingredientsList = ingredient.listIngredientFood(foodID);
            Tool tool = new Tool(Statics.connection.getConnection());
            toolsList = tool.listToolFood(foodID);
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
            String instruction = "";
            if (recipeResultSet != null) {
                System.out.println("recipe post");
                try {
                    while (recipeResultSet.next()) {
                        int step = recipeResultSet.getInt("step");
                        String inst = recipeResultSet.getString("instruction");
                        instruction = instruction + inst + "\n";
                    }
                } catch (SQLException e) {
                    System.out.println("recipe error post");
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("resultset null");
            }
            String ingredientsText = "";
            for (int i = 0; i < ingredientsList[1].size(); i++) {
                if (i < ingredientsList[1].size() - 1)
                    ingredientsText += ingredientsList[1].get(i) + "\n";
                else
                    ingredientsText += ingredientsList[1].get(i);
            }
            String toolsText = "";
            for (int i = 0; i < toolsList[1].size(); i++) {
                if (i < toolsList[1].size() - 1)
                    toolsText += toolsList[1].get(i) + "\n";
                else
                    toolsText += toolsList[1].get(i);
            }
            System.out.println(instruction);

            enterSteps.setText(instruction);
            enterFoodIngredient.setText(ingredientsText);
            enterFoodTools.setText(toolsText);
        }
    }

    public class UpdateRecipeAsync extends AsyncTask<Object,Void,Void> {
        @Override
        protected Void doInBackground(Object... objects) {
            int foodID = (int) Statics.currFood[0];
            Food food = new Food(Statics.connection.getConnection(), Statics.currUserAccount);
            String foodName = (String) objects[0];
            String foodDescription = (String) objects[1];
            byte [] picture = (byte[]) objects[2];
            String instruction = (String) objects[3];
            Statics.check = food.updateFood(foodID, foodName, foodDescription, picture);
            Recipe recipe = new Recipe(Statics.connection.getConnection(), Statics.currUserAccount);
            String[] array = instruction.split("\n");
            ArrayList<String> arrayList = new ArrayList<>();
            for(String text:array) {
                arrayList.add(text);
            }
            Statics.check = recipe.updateRecipe(foodID, arrayList);
            String ingredientsList = (String) objects[4];
            array = ingredientsList.split("\n");
            Ingredient ingredient = new Ingredient(Statics.connection.getConnection());
            arrayList = new ArrayList<>();
            for(String text:array) {
                arrayList.add(text);
                Statics.check = ingredient.createIngredient(text);
            }
            Statics.check = ingredient.updateIngredient(foodID, arrayList);
            ingredient.deleteIngredient();
            Tool tool = new Tool(Statics.connection.getConnection());
            String toolsList = (String) objects[5];
            array = toolsList.split("\n");
            arrayList = new ArrayList<>();
            for(String text:array) {
                arrayList.add(text);
                Statics.check = tool.createTool(text);
            }
            Statics.check = tool.updateToolFood(foodID, arrayList);
            tool.deleteTool();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}