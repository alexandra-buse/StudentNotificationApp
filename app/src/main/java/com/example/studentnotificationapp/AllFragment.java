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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFragment extends Fragment {

    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    private RecyclerView recyclerViewOfAll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.allfragment,null);

        Log.d("MainActivity", "ALL VIEW");
        recyclerViewOfAll = v.findViewById(R.id.recyclerviewofall);
        modelClassArrayList=new ArrayList<>();
        recyclerViewOfAll.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        recyclerViewOfAll.setAdapter(adapter);
        
        findEvents();
        return v;
    }

    private void findEvents() {
        ApiUtilities.getApiInterface().getEvents().enqueue(new Callback<MainEventsFromApi>() {
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
