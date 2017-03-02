package com.example.asheransari.bloodapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.asheransari.bloodapplication.Authentication_Classes.login;
import com.example.asheransari.bloodapplication.variables_classes.new_users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Splash extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("user");

//        if (auth.getCurrentUser().getEmail().toString().equals("")) {
//            Toast.makeText(Splash.this, "user is Sign out", Toast.LENGTH_SHORT).show();
//            Log.e("in If Condition", "in if condition, email =" + auth.getCurrentUser().getEmail().toString());
//        } else {
//            Toast.makeText(Splash.this, "User is Sign in", Toast.LENGTH_SHORT).show();
//
//            Log.e("in Else Condition", "in else condition, email =" + auth.getCurrentUser().getEmail().toString());
//            fetchUserDetail(auth.getCurrentUser().getEmail().toString());
//        }

        boolean check = true;
// extUtils.isEmpty(auth.getCurrentUser().getEmail().toString());
//                    Log.e("check = ",auth.getCurrentUser().getEmail().toString());
//                    if (auth.getCurrentUser().getEmail().toString()==null||auth.getCurrentUser().getEmail().toString().equals("") || TextUtils.isEmpty(auth.getCurrentUser().getEmail().toString())) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
//                        Toast.makeText(Splash.this, "user is Sign out", Toast.LENGTH_SHORT).show();
//                        Log.e("in If Condition", "in if condition, email =" + auth.getCurrentUser().getEmail().toString());       T\
//            Toast.makeText(this, "if condition", Toast.LENGTH_SHORT).show();
            check = false;
        } else {
//            Toast.makeText(this, "else condition", Toast.LENGTH_SHORT).show();
            check = true;
//                        Toast.makeText(Splash.this, "User is Sign in", Toast.LENGTH_SHORT).show();
//                        Log.e("in Else Condition", "in else condition, email =" + auth.getCurrentUser().getEmail().toString());
//            fetchUserDetail(auth.getCurrentUser().getEmail().toString());
        }

        final boolean finalCheck = check;
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    Log.e("Launching try", "Launched");
                    sleep(3000);
                } catch (Exception e) {
                    Log.e("Launching Exception", "Launched");
                    e.printStackTrace();
                } finally {
                    if (!finalCheck) {
                        Intent i = new Intent(Splash.this,login.class);
                        startActivity(i);
                        finish();
                    } else {
                        fetchUserDetail(auth.getCurrentUser().getEmail().toString());
                    }
                }

            }
        };
        timer.start();


    }

    private void fetchUserDetail(final String email) {
//        Log.e("email", "" + email);
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    new_users users = dataSnapshot.getValue(new_users.class);
//                    Log.e("check ", "" + dataSnapshot);

                    ArrayList<new_users> arrayList = new ArrayList<>();
                    arrayList.add(users);

                    Log.e("length", String.valueOf(arrayList.size()));
                    for (int i = 0; i < arrayList.size(); i++) {
                        Log.e("count1", "" + arrayList.get(i).getEmail());

                        if (email.equals(arrayList.get(i).getEmail())) {
                            Toast.makeText(Splash.this, "in fetchUserDetail method and further in if condition", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(Splash.this, main_panel.class);
                            a.putExtra("name", arrayList.get(i).getName());
                            a.putExtra("group", arrayList.get(i).getBlood());
                            a.putExtra("email", arrayList.get(i).getEmail());
                            startActivity(a);
                            finish();
                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

}