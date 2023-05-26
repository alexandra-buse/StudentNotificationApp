package com.example.studentnotificationapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentnotificationapp.MainActivity;
import com.example.studentnotificationapp.User;
import com.example.studentnotificationapp.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Vector;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FirebaseUser user;
    private DatabaseReference database;

    private String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            database = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();

            final TextView textView = binding.textHome;

            database.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);

                    if (userProfile != null) {
                        String userName = userProfile.fullName;
                        Vector<String> allTopics = userProfile.getVectorOfTopics();
                        textView.setText(userName);
                        binding.userSectie.setText(userProfile.Sectie);
                        binding.userCiclu.setText(userProfile.Ciclu);
                        binding.userAnStudii.setText("Anul " + userProfile.An);
                        binding.userSerie.setText("Seria " + userProfile.Serie);
                        binding.userGrupa.setText("Grupa " + userProfile.Grupa);
                        for (int i = 0; i < allTopics.size(); i++) {
                            FirebaseMessaging.getInstance().subscribeToTopic(allTopics.get(i));
                        }


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Something wrong!", Toast.LENGTH_LONG).show();
                }
            });

        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}