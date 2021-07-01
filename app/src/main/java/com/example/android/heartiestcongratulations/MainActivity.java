package com.example.android.heartiestcongratulations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView bhaijiImage,diduImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bhaijiImage=findViewById(R.id.bhaiji);
        diduImage=findViewById(R.id.didu);

        diduImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),DiduBdayVideo.class);
                startActivity(i);

            }
        });
        bhaijiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),BhaijiBdayVideo.class);
                startActivity(i);

            }
        });
    }
}
