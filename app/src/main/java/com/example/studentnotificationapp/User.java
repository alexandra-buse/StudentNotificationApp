package com.example.studentnotificationapp;

import android.util.Log;

import java.util.Vector;

public class User {
    public String fullName, email, An, Ciclu, Grupa, Sectie, Serie, Topics;
    public Boolean admin;


    public User() {
    }

    public User(String fullName, String email, Boolean admin) {
        this.fullName = fullName;
        this.email = email;
        this.admin = admin;
    }

    public Vector<String> getVectorOfTopics() {
        Vector <String> vector = new Vector<String>();

        vector.add(this.An);
        vector.add(this.Ciclu);
        vector.add(this.Grupa);
        vector.add(this.Sectie);
        vector.add(this.Serie);

        if (Topics != null) {
            String[] otherTopics = Topics.split(";");

            for (int i = 0; i < otherTopics.length; i++) {
                vector.add(otherTopics[i]);
            }
        }

        return vector;

    }

}
