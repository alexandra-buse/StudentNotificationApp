package com.example.studentnotificationapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_activity);
        Log.d("MainActivity", "Should be here!");

        // get the event details from the intent
        Intent intent = getIntent();
        String eventTitle = intent.getStringExtra("eventTitle");
        String eventDescription = intent.getStringExtra("eventDescription");
        String eventCategory = intent.getStringExtra("eventCategory");

        // populate the views in your layout with the event details
        TextView titleView = findViewById(R.id.eventTitle);
        TextView descriptionView = findViewById(R.id.eventDescription);
        TextView categoryView = findViewById(R.id.eventCategory);

        titleView.setText(eventTitle);
        descriptionView.setText(eventDescription);
        categoryView.setText(eventCategory);

        ImageView imageView = findViewById(R.id.eventImage);

        if (categoryView.getText().equals("Tech")) {
            imageView.setImageResource(R.drawable.tech_logo);
        }
        else if (categoryView.getText().equals("Arts")) {
            imageView.setImageResource(R.drawable.artlogo);
        }
        else if (categoryView.getText().equals("Culture")) {
            imageView.setImageResource(R.drawable.culture_logo);
        }
        else if (categoryView.getText().equals("Music")) {
            imageView.setImageResource(R.drawable.music_logo);
        }
        else if (categoryView.getText().equals("Sports")) {
            imageView.setImageResource(R.drawable.sport_logo);
        }
        else {
            imageView.setImageResource(R.drawable.courses_logo);
        }

        // set up the Like and Add to Calendar buttons
        Button likeButton = findViewById(R.id.likeButton);
        Button addToCalendarButton = findViewById(R.id.addToCalendarButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // handle like button click
            }
        });
        addToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // handle add to calendar button click
            }
        });

    }
}
