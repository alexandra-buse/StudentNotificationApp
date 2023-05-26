package com.example.studentnotificationapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    String Base_URL = "http://10.0.2.2:5000";

    @GET("/events")
    Call<MainEventsFromApi>getEvents();

    @GET("/events/liked/")
    Call<MainEventsFromApi>getLikedEvents(@Query("uid") String uid);

    @GET("/events/recommended/")
    Call<MainEventsFromApi>getRecommendedEvents(@Query("uid") String uid);

    @GET("/events/school/")
    Call<MainEventsFromApi>getSchoolEvents(@Query("uid") String uid);

    @GET("/events/school/")
    Call<MainEventsFromApi>getCalendarEvents(@Query("uid") String uid);

    @GET("/interaction")
    Call<MainInteractionsFromApi>getInteractionWithEvent(@Query("uid") String uid,  @Query("event") String event);
}
