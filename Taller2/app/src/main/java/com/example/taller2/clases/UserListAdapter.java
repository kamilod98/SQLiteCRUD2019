package com.example.taller2.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.taller2.R;

public class UserListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private String[][] listItem;

    public UserListAdapter(Context context, String[][] listItem){
        super(context, -1, listItem[0]);
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list, parent, false);
        TextView name = rowView.findViewById(R.id.showUsername);
        TextView email = rowView.findViewById(R.id.showEmail);
        ImageView img = rowView.findViewById(R.id.image);
        name.setText(listItem[0][position]);
        email.setText(listItem[1][position]);
        return rowView;
    }
}
