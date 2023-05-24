package com.example.studentnotificationapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForYouFragment extends Fragment {

    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    private RecyclerView recyclerViewOfAll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.foryoufragment,null);

        Log.d("MainActivity", "FOR YOU VIEW");
        recyclerViewOfAll = v.findViewById(R.id.recyclerviewofforyou);
        modelClassArrayList=new ArrayList<>();
        recyclerViewOfAll.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewOfAll.setAdapter(adapter);

        findEvents();
        return v;
    }

    private void findEvents() {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        ApiUtilities.getApiInterface().getRecommendedEvents(uid).enqueue(new Callback<MainEventsFromApi>() {
            @Override
            public void onResponse(Call<MainEventsFromApi> call, Response<MainEventsFromApi> response) {
                Log.d("MainActivity", "SOMETHING");
                if (response.isSuccessful()) {
                    modelClassArrayList.addAll(response.body().getEvents());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainEventsFromApi> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
                //Log.d("Main Activity", t);
            }
        });
    }
}
