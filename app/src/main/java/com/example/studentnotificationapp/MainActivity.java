package com.example.studentnotificationapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends MenuActivity {
    EditText etToken;
    private Button logOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       // etToken = findViewById(R.id.etToken);
       // logOut = findViewById(R.id.signOut);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {


//            logOut.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    FirebaseAuth.getInstance().signOut();
//                    startActivity(new Intent(MainActivity.this, Login.class));
//
//                }
//            });


            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                System.out.println("Fetching FCM registration token failed");
                                return;
                            }

                            // Get new FCM registration token
                            String token = task.getResult();

                            // Log and toast
                            //System.out.println(token);
                            //Toast.makeText(MainActivity.this, "Your token is " + token, Toast.LENGTH_SHORT).show();
                          //  etToken.setText(token);

                            FirebaseMessaging.getInstance().subscribeToTopic("demo-topic");
                        }
                    });
        }
        else {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }
}