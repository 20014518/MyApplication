package sg.edu.rp.c346.id20014518.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Hobby> hobbies;

    public CustomAdapter(Context context, int resource, ArrayList<Hobby> objects) {
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.hobbies = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvDescription = rowView.findViewById(R.id.tvDescription);
        //TextView tvStars = rowView.findViewById(R.id.tvStars);
        RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);

        // Obtain the Android Version information based on the position
        Hobby currentHobby = hobbies.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentHobby.getTitle());
        tvDescription.setText(currentHobby.getDescription());
        String stars = "";
        for(int i = 0; i < currentHobby.getStars(); i++){
            stars += " * ";
        }
        //tvStars.setText(stars);

        ratingBar.setRating(currentHobby.getStars());


        return rowView;
    }

}

