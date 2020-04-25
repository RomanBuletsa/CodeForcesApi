package com.example.yaroslav.codeforcesapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class StartPage extends AppCompatActivity {

    ImageButton imageButton;
    ImageView stars;
    Animation anim;
    Animation logoanim;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start_page);

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        stars = (ImageView) findViewById(R.id.frontstars);
        logo = (ImageView) findViewById(R.id.logocf);

        //Animations
        anim = AnimationUtils.loadAnimation(this, R.anim.loaderanim);
        logoanim = AnimationUtils.loadAnimation(this, R.anim.rotatelogo);

        anim.setRepeatCount(Animation.INFINITE);
        logoanim.setRepeatCount(Animation.INFINITE);

        logo.startAnimation(logoanim);
        stars.startAnimation(anim);



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartPage.this, LoadUsers.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
