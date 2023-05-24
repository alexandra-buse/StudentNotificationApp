package com.example.studentnotificationapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelClassArrayList;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main Event", "Clicked");
                Intent intent = new Intent(context, EventDetailActivity.class);
                ModelClass selectedEvent = modelClassArrayList.get(position);
                intent.putExtra("eventTitle", selectedEvent.getTitle());
                intent.putExtra("eventDescription", selectedEvent.getDescription());
                intent.putExtra("eventCategory", selectedEvent.getCategory());
                // add any other event details that you want to pass to the new activity
                context.startActivity(intent);
            }
        });

        holder.mTitle.setText(modelClassArrayList.get(position).getTitle());
        holder.mDescription.setText(modelClassArrayList.get(position).getDescription());
        holder.mCategory.setText(modelClassArrayList.get(position).getCategory());

        Log.d("MainActivity", (String) holder.mCategory.getText());

        if (holder.mCategory.getText().equals("Tech")) {
            holder.imageView.setImageResource(R.drawable.tech_logo);
        }
        else if (holder.mCategory.getText().equals("Arts")) {
            holder.imageView.setImageResource(R.drawable.artlogo);
        }
        else if (holder.mCategory.getText().equals("Culture")) {
            holder.imageView.setImageResource(R.drawable.culture_logo);
        }
        else if (holder.mCategory.getText().equals("Music")) {
            holder.imageView.setImageResource(R.drawable.music_logo);
        }
        else if (holder.mCategory.getText().equals("Sports")) {
            holder.imageView.setImageResource(R.drawable.sport_logo);
        }
        else {
            holder.imageView.setImageResource(R.drawable.courses_logo);
        }
    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mDescription,mCategory;
        CardView cardView;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.mainTitle);
            mDescription=itemView.findViewById(R.id.description);
            mCategory=itemView.findViewById(R.id.category);
            imageView = itemView.findViewById(R.id.imageview);
            cardView = itemView.findViewById(R.id.cardview);



        }
    }
}
