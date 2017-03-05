package com.example.asheransari.bloodapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asheransari.bloodapplication.Adapters.Feed_home_adapter;
import com.example.asheransari.bloodapplication.variables_classes.Required_variable_blood;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView mRecyclerView;
    private Feed_home_adapter mFeedHomeAdapter;
    private ArrayList<Required_variable_blood> datalist;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChildEventListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference().child("required");

        TextView comment = (TextView)v.findViewById(R.id.list_post_comment_lbl);
        TextView volunteer = (TextView)v.findViewById(R.id.list_post_volunteer_lbl);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        datalist = new ArrayList<>();

        RecyclerView.LayoutManager manager = new GridLayoutManager(this.getContext(),1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getCompleteDataFromFirebase();

//        mRecyclerView.
//        comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Home.this.getContext(),Volunteer_Comment.class);
//                startActivityForResult(i,12);
////                i.putExtra("data",datalist);
//            }
//        });
//        volunteer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        mRecyclerView.setAdapter(mFeedHomeAdapter);
//        mFeedHomeAdapter =new Feed_home_adapter(this.getContext(), datalist);

//        mFeedHomeAdapter = new Feed_home_adapter();
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getCompleteDataFromFirebase();
    }

    private void getCompleteDataFromFirebase()
    {
        if (mChildEventListener == null){
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Required_variable_blood variableBlood = dataSnapshot.getValue(Required_variable_blood.class);
                    datalist.add(variableBlood);
                    mFeedHomeAdapter =new Feed_home_adapter(getContext(), datalist);
                    mRecyclerView.setAdapter(mFeedHomeAdapter);

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
            mReference.addChildEventListener(mChildEventListener);
        }
    }

}
