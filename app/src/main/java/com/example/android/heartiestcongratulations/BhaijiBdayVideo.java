package com.example.android.heartiestcongratulations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BhaijiBdayVideo extends AppCompatActivity {
    private TextInputEditText name_text;
    private TextInputEditText wish_text;
    String name,wish;
    private Button button;
    private MediaController mediaController;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhaiji_bday_video);
        VideoView mVideoView = (VideoView)findViewById(R.id.videoview_bh);
        name_text=(TextInputEditText)findViewById(R.id.name_text_bh);
        wish_text=(TextInputEditText)findViewById(R.id.wish_text_bh);
        button=findViewById(R.id.button);


//Here insert your B'day video. I intentionally replaced my brother's b'day video with some other video.
        String uriPath = "android.resource://com.example.android.heartiestcongratulations/"+R.raw.video;

        Uri uri = Uri.parse(uriPath);
        mVideoView.setVideoURI(uri);
        mediaController = new MediaController(BhaijiBdayVideo.this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.requestFocus();
        mVideoView.start();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                name=name_text.getText().toString();
                wish=wish_text.getText().toString();
                reference= FirebaseDatabase.getInstance("https://heartiestcongratulations-71f71-default-rtdb.firebaseio.com/").
                        getReference("BdayWishesList/Bhaiji");
                if(!TextUtils.isEmpty(name) && (!TextUtils.isEmpty(wish))){
                    String id=reference.push().getKey();
                    reference.child(id).child("name").setValue(name);
                    reference.child(id).child("wish").setValue(wish);
                    Toast.makeText(getApplicationContext(),"Your wish added to the wishlist.Thank you!",Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(getApplicationContext(), BhaijiBdayWishes.class);
                startActivity(i);
            }
        });
    }


}
