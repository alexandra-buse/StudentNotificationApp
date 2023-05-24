package com.example.studentnotificationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText fullName, email, password;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        regButton = findViewById(R.id.registerButton);
        regButton.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String nameS = fullName.getText().toString().trim();
        String emailS = email.getText().toString().trim();
        String passwordS = password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(emailS, passwordS)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(nameS, emailS, true);

                            FirebaseDatabase.getInstance().getReference("Users").
                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Toast.makeText(Register.this, "User registered successfully!", Toast.LENGTH_LONG).show();

                                                startActivity(new Intent(Register.this, MainActivity.class));
                                            }
                                            else {
                                                Toast.makeText(Register.this, "Could not register the user!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}