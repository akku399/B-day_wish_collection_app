package com.example.android.heartiestcongratulations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BhaijiBdayWishes extends AppCompatActivity {
    private RecyclerView rv;
    private static final String TAG = "DidiBdayWishes";
    DatabaseReference reference;
    private ProgressDialog progress;
    private Handler progressBarHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhaiji_bday_wishes);
        rv = findViewById(R.id.r_view_bh);
        rv.setHasFixedSize(false);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new LinearLayoutManager(this));
        reference= FirebaseDatabase.getInstance("https://heartiestcongratulations-71f71-default-rtdb.firebaseio.com/").
                getReference("BdayWishesList/Bhaiji");

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<didiBDayModel, BhaijiBdayViewModel> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<didiBDayModel, BhaijiBdayViewModel>(
                        didiBDayModel.class,
                        R.layout.row,
                        BhaijiBdayViewModel.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(BhaijiBdayViewModel bhaijiBdayViewModel, didiBDayModel bDayModel, int i) {
                        bhaijiBdayViewModel.setBdayData(getApplication(), bDayModel.getName(),bDayModel.getWish());
                    }


                };


        uploadWishes();
        rv.setAdapter(firebaseRecyclerAdapter);


    }
    public void uploadWishes(){
        progress=new ProgressDialog(this);
        progress.setMessage("Uploading Wishes");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setMax(100);
        progress.setProgress(0);

        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            int jumpTime = 0;
            @Override
            public void run() {


                while(jumpTime < totalProgressTime) {
                    jumpTime += 10;
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progress.setProgress(jumpTime);
                        }
                    });
                }
                if (jumpTime >= totalProgressTime) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress.dismiss();
                }

            }
        };
        t.start();
    }
}
