package com.example.studentnotificationapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity {
    Boolean clicked = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_activity);


        // get the event details from the intent
        Intent intent = getIntent();
        String eventTitle = intent.getStringExtra("eventTitle");
        String eventDescription = intent.getStringExtra("eventDescription");
        String eventCategory = intent.getStringExtra("eventCategory");
        String eventId = intent.getStringExtra("eventId");
        String eventDate = intent.getStringExtra("eventDate");
        String eventHour = intent.getStringExtra("eventHour");


        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        ApiUtilities.getApiInterface().getInteractionWithEvent(uid, eventId).enqueue(new Callback<MainInteractionsFromApi>() {
            @Override
            public void onResponse(Call<MainInteractionsFromApi> call, Response<MainInteractionsFromApi> response) {
                Log.d("MainActivity", "SOMETHING");
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "Response");
                    ArrayList<InteractionsClass> currentInteraction = response.body().getInteractions();
                }
            }

            @Override
            public void onFailure(Call<MainInteractionsFromApi> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }


        });


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
        ImageView likeButton = findViewById(R.id.likeView);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked) {
                    clicked = false;
                    likeButton.setImageResource(R.drawable.filled_heart);
                } else {
                    clicked = true;
                    likeButton.setImageResource(R.drawable.border_heart);
                }
            }
        });

        Button addToCalendarButton = findViewById(R.id.addToCalendarButton);
//        likeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // handle like button click
//            }
//        });
        addToCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // handle add to calendar button click
            }
        });

    }
}
