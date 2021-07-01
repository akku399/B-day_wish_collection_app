package com.example.android.heartiestcongratulations;

import android.app.Application;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BhaijiBdayViewModel extends RecyclerView.ViewHolder {
    private static final String TAG = "DidiBdayViewModel";
    View mView;
    DatabaseReference reference ;
    DataSnapshot dataSnap;




    public BhaijiBdayViewModel(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        reference= FirebaseDatabase.getInstance("https://heartiestcongratulations-71f71-default-rtdb.firebaseio.com/").
                getReference("BdayWishesList/Bhaiji");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshots) {
                dataSnap = dataSnapshots;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void setBdayData(final Application ctx, String name, String wish ){
        TextView user_name=mView.findViewById(R.id.name);
        TextView user_wish=mView.findViewById(R.id.wish);
        user_name.setText(name);
        user_wish.setText(wish);
    }
}
