package com.example.studentnotificationapp;

import android.graphics.Color;

import android.icu.util.Calendar;
import android.net.ParseException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class CalendarFragment extends Fragment {

    ArrayList<ModelClass> modelClassArrayList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendar.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private MaterialCalendarView mCalendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        mCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);

        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                        .setCalendarDisplayMode(CalendarMode.MONTHS)
                                .commit();


        // Set a listener to detect when the user selects a date
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //Toast.makeText(this)
            }
        });

          List<CalendarDay> eventDays = getEventDays();




        return view;
    }

    public List<CalendarDay> getEventDays() {
        List<CalendarDay> eventDays = new ArrayList<>();

        modelClassArrayList=new ArrayList<>();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        ApiUtilities.getApiInterface().getCalendarEvents(uid).enqueue(new Callback<MainEventsFromApi>() {
            @Override
            public void onResponse(Call<MainEventsFromApi> call, Response<MainEventsFromApi> response) {
                Log.d("MainActivity", "SOMETHING");
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "Response");
                    modelClassArrayList.addAll(response.body().getEvents());

                    for (int i = 0; i < modelClassArrayList.size(); i++) {
                        Log.d("MainActivity", "Looping");
                        String date = modelClassArrayList.get(i).getDate();
                        Log.d("MainActivity", date);
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        try {
                            Date eventDate = format.parse(date);
                            CalendarDay day = CalendarDay.from(eventDate);
                            Log.d("MainActivity", day.toString());
                            eventDays.add(day);
                        } catch (ParseException | java.text.ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    int eventColor = Color.RED; // or any other color you want
                    Log.d("MainActivity", "Add decorator");
                    EventDecorator eventDecorator = new EventDecorator(eventColor, eventDays);
                    mCalendarView.addDecorator(eventDecorator);
                }
            }

            @Override
            public void onFailure(Call<MainEventsFromApi> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });



        return eventDays;
    }


}