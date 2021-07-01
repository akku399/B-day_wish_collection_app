package com.example.android.heartiestcongratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DidiBdayWishes extends AppCompatActivity {


    private RecyclerView rv;
    private static final String TAG = "DidiBdayWishes";
    DatabaseReference reference;
    private ProgressDialog progress;
    private Handler progressBarHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_didi_bday_wishes);

        //loadData();

      /* final ArrayList<didiBDayModel> wishesList= new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    String keys = datas.getKey();
                    String user_name = datas.child("/name").getValue().toString();
                    String user_wish = datas.child("/wish").getValue().toString();
                    if((!user_name.isEmpty()) && (!user_wish.isEmpty()))
                    wishesList.add(new didiBDayModel(user_name, user_wish));
                    Log.d(TAG, "loadData1: "+user_name+" "+user_wish);
                    Log.d(TAG, "loadData2: "+wishesList.get(0).getName()+" "+
                            wishesList.get(0).getWish()+" "+wishesList.size());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Log.d(TAG, "onCreate: wishList size "+wishesList.size());
        DidiBdayAdapter adapter = new DidiBdayAdapter(this, wishesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);*/

        rv = findViewById(R.id.r_view);
        rv.setHasFixedSize(false);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new LinearLayoutManager(this));
        reference= FirebaseDatabase.getInstance("https://heartiestcongratulations-71f71-default-rtdb.firebaseio.com/").
                getReference("BdayWishesList/Didi");

      /*  Intent intent = getIntent();
        name = intent.getExtras().getString("name","Mony");
        wish = intent.getExtras().getString("wish","Happy Wala B'Day");
        if((!name.isEmpty()) && (!wish.isEmpty()))
        wishesList.add(new didiBDayModel(name, wish));
        Log.d(TAG, "onCreate: "+name+" "+wish);
        //Log.d(TAG, "onCreate: "+wishesList.get(0).name+" "+wishesList.get(0).wish);
        SharedPreferences sharedPreferences =  getApplicationContext()
                .getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(wishesList);
        editor.putString("wishes", json);
        editor.apply();*/



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
        FirebaseRecyclerAdapter<didiBDayModel, DidiBdayViewModel> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<didiBDayModel, DidiBdayViewModel>(
                        didiBDayModel.class,
                                        R.layout.row,
                        DidiBdayViewModel.class,
                                        reference
                                ) {
                    @Override
                    protected void populateViewHolder(DidiBdayViewModel didiBdayViewModel, didiBDayModel bDayModel, int i) {
                        didiBdayViewModel.setBdayData(getApplication(), bDayModel.getName(),bDayModel.getWish());
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
