package com.example.easymealprep;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private Activity activity;
    private ArrayList<String> entryData;
    

    public ListAdapter(Context context, int resource, ArrayList arrData) {
        super();
        this.context = context;
        this.resource = resource;
        this.entryData = arrData;
    }

    public int getCount() {
        // return the number of records
        return entryData.size();
    }

    // getView method is called for each item of ListView
    public View getView(final int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, parent, false);
       // view = inflater.inflate(R.layout.customlistlayout, parent, false);
       // view = inflater.inflate(R.layout.customlistlayout, null, true);


        // get the reference of textView and button
        TextView title = (TextView) view.findViewById(R.id.recipeName);
        final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteBtn);
        final ImageButton editButton = (ImageButton)view.findViewById(R.id.editBtn);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        // Set the title and button name
        title.setText(entryData.get(position));
        //btnAction.setText("Action " + position);

        // Click listener of button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Logic for delete
                new DeleteFoodAsync().execute(entryData.get(position));
                entryData.remove(entryData.get(position));
                notifyDataSetChanged();

            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Logic for Edit

                notifyDataSetChanged();

            }
        });

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class DeleteFoodAsync extends AsyncTask<String,Void,Void> {
        Food food;
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
        }
    }
}

