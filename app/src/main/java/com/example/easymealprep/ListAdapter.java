package com.example.easymealprep;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.recipeName);
        final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteBtn);

        // Set the title and button name
        txtSchoolTitle.setText(entryData.get(position));
        //btnAction.setText("Action " + position);

        // Click listener of button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Logic for delete

                entryData.remove(entryData.get(position));
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
    }}