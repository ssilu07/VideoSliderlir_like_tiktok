package com.study.videoslider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class MainActivity extends AppCompatActivity {

    RecyclerView Mrecyclerview;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mrecyclerview = findViewById(R.id.recyclerview_video);
        Mrecyclerview.setHasFixedSize(true);
        Mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("video"); //check

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<member,ViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<member, ViewHolder>(
                        member.class,
                        R.layout.row,
                        ViewHolder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, member member, int i) {
                        viewHolder.setVideo(getApplication(),member.getTitle(),member.getUrl());
                    }
                };
        Mrecyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
