package com.example.android.heartiestcongratulations;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

class DidiBdayAdapter extends RecyclerView.Adapter<DidiBdayAdapter.Viewholder> {

    private Context context;
    private ArrayList<didiBDayModel> wishesList;

    // Constructor
    public DidiBdayAdapter(Context context, ArrayList<didiBDayModel> wishesList) {
        this.context = context;
        this.wishesList = wishesList;
    }

    @NonNull
    @Override
    public DidiBdayAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DidiBdayAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        didiBDayModel model = wishesList.get(position);
        holder.name.setText(model.getName());
        holder.wish.setText(model.getWish());
    }

    @Override
    public int getItemCount() {
        return wishesList.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView wish;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            wish = itemView.findViewById(R.id.wish);
        }
    }
}
