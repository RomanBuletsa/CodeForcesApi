package com.example.yaroslav.codeforcesapi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComparisonActivity extends AppCompatActivity {

    TextView Username1, Username2;
    ImageView imageView1,imageView2, BattleLogo;
    int numberofattempts1, numberofattempts2, numberofsuccesses1=0, numberofsuccesses2=0,
            minrating1=10000, minrating2=10000, contest1, contest2, maxup1=0, maxdown1=0, maxup2=0, maxdown2=0;
    TextView Numberofattempts1, Numberofattempts2, Numberofsuccesses1, Numberofsuccesses2,
            Minrating1, Minrating2, Contest1, Contest2, Maxup1, Maxdown1, Maxup2, Maxdown2, Maxrating1, Maxrating2, Rating1, Rating2, Contribution1,
            Contribution2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Battle");

        setContentView(R.layout.activity_comparison);

        Username1 = (TextView) findViewById(R.id.username1);
        Username2 = (TextView) findViewById(R.id.username2);

        imageView1 = (ImageView) findViewById(R.id.user_image_header1);
        imageView2 = (ImageView) findViewById(R.id.user_image_header2);

        Numberofattempts1 = (TextView) findViewById(R.id.numberofattempts1);
        Numberofattempts2 = (TextView) findViewById(R.id.numberofattempts2);

        Numberofsuccesses1 = (TextView) findViewById(R.id.numberofsuccesses1);
        Numberofsuccesses2 = (TextView) findViewById(R.id.numberofsuccesses2);

        Minrating1 = (TextView) findViewById(R.id.minrating1);
        Minrating2 = (TextView) findViewById(R.id.minrating2);

        Contest1 = (TextView) findViewById(R.id.contest1);
        Contest2 = (TextView) findViewById(R.id.contest2);

        Maxup1 = (TextView) findViewById(R.id.maxup1);
        Maxup2 = (TextView) findViewById(R.id.maxup2);

        Maxdown1 = (TextView) findViewById(R.id.maxdown1);
        Maxdown2 = (TextView) findViewById(R.id.maxdown2);

        Maxrating1 = findViewById(R.id.maxRating1);
        Maxrating2 = findViewById(R.id.maxRating2);

        Rating1 = findViewById(R.id.rating1);
        Rating2 = findViewById(R.id.rating2);

        Contribution1 = findViewById(R.id.contribution1);
        Contribution2 = findViewById(R.id.contribution2);

        BattleLogo = findViewById(R.id.battlelogo);



        String avatarUrl1 = getIntent().getExtras().getString("avatar_url1");
        String avatarUrl2 = getIntent().getExtras().getString("avatar_url2");


        String username1 = getIntent().getExtras().getString("login1");
        String username2 = getIntent().getExtras().getString("login2");


        //  ЦЕ ПОТРЫБНО ВИВЕСТИ

        String rating1 = getIntent().getExtras().getString("rating1");
        String rating2 = getIntent().getExtras().getString("rating2");

        String maxRating1 = getIntent().getExtras().getString("maxRating1");
        String maxRating2 = getIntent().getExtras().getString("maxRating2");


        String contribution1 = getIntent().getExtras().getString("contribution1");
        String contribution2 = getIntent().getExtras().getString("contribution2");

        //

        Username1.setText(username1);
        Username2.setText(username2);

        Rating1.setText(rating1);
        Rating2.setText(rating2);
        if(Integer.parseInt(rating1) > Integer.parseInt(rating2)){
            Rating1.setTextColor(Color.parseColor("#b2fc58"));
            Rating2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Rating2.setTextColor(Color.parseColor("#b2fc58"));
            Rating1.setTextColor(Color.parseColor("#e23b1d"));
        }

        Maxrating1.setText(maxRating1);
        Maxrating2.setText(maxRating2);
        if(Integer.parseInt(maxRating1) > Integer.parseInt(maxRating2)){
            Maxrating1.setTextColor(Color.parseColor("#b2fc58"));
            Maxrating2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Maxrating2.setTextColor(Color.parseColor("#b2fc58"));
            Maxrating1.setTextColor(Color.parseColor("#e23b1d"));
        }

        Contribution1.setText(contribution1);
        Contribution2.setText(contribution2);
        if(Integer.parseInt(contribution1) > Integer.parseInt(contribution2)){
            Contribution1.setTextColor(Color.parseColor("#b2fc58"));
            Contribution2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Contribution2.setTextColor(Color.parseColor("#b2fc58"));
            Contribution1.setTextColor(Color.parseColor("#e23b1d"));
        }

        Glide.with(this)
                .load(avatarUrl1)
                .into(imageView1);

        Glide.with(this)
                .load(avatarUrl2)
                .into(imageView2);

        imageView1.setAnimation(AnimationUtils.loadAnimation(this, R.anim.move_from_left));
        imageView2.setAnimation(AnimationUtils.loadAnimation(this,R.anim.move_from_right));
        Username1.setAnimation(AnimationUtils.loadAnimation(this, R.anim.move_from_left));
        Username2.setAnimation(AnimationUtils.loadAnimation(this,R.anim.move_from_right));

        BattleLogo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.battle_icon_anim));


        Service_Submission apiService = Client.getClient().create(Service_Submission.class);

        Call<SubmissionList> call1 = apiService.getList(username1);
        Call<SubmissionList> call2 = apiService.getList(username2);

        call1.enqueue(new Callback<SubmissionList>() {
            @Override
            public void onResponse(Call<SubmissionList> call, Response<SubmissionList> response) {
                List<Submission> Submission = response.body().getSubmission();

                numberofattempts1=Submission.size();

                for (int i = 0; i < Submission.size(); i++){
                    String verdict = Submission.get(i).getVerdict();
                    String level = Submission.get(i).getProblem().getIndex();

                    if (level.length()==1 && (verdict).equals("OK")){
                        numberofsuccesses1++;
                    }
                }

                ch1();
            }

            @Override
            public void onFailure(Call<SubmissionList> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });

        call2.enqueue(new Callback<SubmissionList>() {
            @Override
            public void onResponse(Call<SubmissionList> call, Response<SubmissionList> response) {
                List<Submission> Submission = response.body().getSubmission();

                numberofattempts2=Submission.size();

                for (int i = 0; i < Submission.size(); i++){
                    String verdict = Submission.get(i).getVerdict();
                    String level = Submission.get(i).getProblem().getIndex();

                    if (level.length()==1 && (verdict).equals("OK")){
                        numberofsuccesses2++;
                    }
                }

                ch1();
            }

            @Override
            public void onFailure(Call<SubmissionList> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });


        Service_Rating apiService1 = Client.getClient().create(Service_Rating.class);
        Call<RatingChangeList> call3 = apiService1.getList(username1);
        Call<RatingChangeList> call4 = apiService1.getList(username2);

        call3.enqueue(new Callback<RatingChangeList>() {
            @Override
            public void onResponse(Call<RatingChangeList> call, Response<RatingChangeList> response) {
                List<RatingChange> RatingChange = response.body().getRatingChange();
                contest1=RatingChange.size();
                for (int i = 0; i < RatingChange.size(); i++){
                    int oldr=Integer.parseInt(RatingChange.get(i).getOldRating());
                    int newr=Integer.parseInt(RatingChange.get(i).getNewRating());
                    if(newr>oldr) maxup1=Math.max(maxup1,newr-oldr);
                    if(newr<oldr) maxdown1=Math.max(maxdown1,oldr-newr);
                    minrating1=Math.min(minrating1,newr);
                }

                ch2();
            }

            @Override
            public void onFailure(Call<RatingChangeList> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });


        call4.enqueue(new Callback<RatingChangeList>() {
            @Override
            public void onResponse(Call<RatingChangeList> call, Response<RatingChangeList> response) {
                List<RatingChange> RatingChange = response.body().getRatingChange();
                contest2=RatingChange.size();
                for (int i = 0; i < RatingChange.size(); i++){
                    int oldr=Integer.parseInt(RatingChange.get(i).getOldRating());
                    int newr=Integer.parseInt(RatingChange.get(i).getNewRating());
                    if(newr>oldr) maxup2=Math.max(maxup2,newr-oldr);
                    if(newr<oldr) maxdown2=Math.max(maxdown2,oldr-newr);
                    minrating2=Math.min(minrating2,newr);
                }

                ch2();

            }

            @Override
            public void onFailure(Call<RatingChangeList> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });




    }

    private void ch1(){
        Numberofattempts1.setText(Integer.toString(numberofattempts1));
        Numberofattempts2.setText(Integer.toString(numberofattempts2));
        if(numberofattempts1 > numberofattempts2){
            Numberofattempts1.setTextColor(Color.parseColor("#b2fc58"));
            Numberofattempts2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Numberofattempts2.setTextColor(Color.parseColor("#b2fc58"));
            Numberofattempts1.setTextColor(Color.parseColor("#e23b1d"));
        }

        Numberofsuccesses1.setText(Integer.toString(numberofsuccesses1));
        Numberofsuccesses2.setText(Integer.toString(numberofsuccesses2));
        if(numberofsuccesses1 > numberofsuccesses2){
            Numberofsuccesses1.setTextColor(Color.parseColor("#b2fc58"));
            Numberofsuccesses2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Numberofsuccesses2.setTextColor(Color.parseColor("#b2fc58"));
            Numberofsuccesses1.setTextColor(Color.parseColor("#e23b1d"));
        }

    }

    private void ch2(){

        Contest1.setText(Integer.toString(contest1));
        Contest2.setText(Integer.toString(contest2));
        if(contest1 > contest2){
            Contest1.setTextColor(Color.parseColor("#b2fc58"));
            Contest2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Contest2.setTextColor(Color.parseColor("#b2fc58"));
            Contest1.setTextColor(Color.parseColor("#e23b1d"));
        }

        Maxup1.setText("+"+Integer.toString(maxup1));
        Maxup2.setText("+"+Integer.toString(maxup2));
        if(maxup1 > maxup2){
            Maxup1.setTextColor(Color.parseColor("#b2fc58"));
            Maxup2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Maxup2.setTextColor(Color.parseColor("#b2fc58"));
            Maxup1.setTextColor(Color.parseColor("#e23b1d"));
        }

        Maxdown1.setText("-"+Integer.toString(maxdown1));
        Maxdown2.setText("-"+Integer.toString(maxdown2));
        if(maxdown1 > maxdown2){
            Maxdown2.setTextColor(Color.parseColor("#b2fc58"));
            Maxdown1.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Maxdown1.setTextColor(Color.parseColor("#b2fc58"));
            Maxdown2.setTextColor(Color.parseColor("#e23b1d"));
        }

        Minrating1.setText(Integer.toString(minrating1));
        Minrating2.setText(Integer.toString(minrating2));
        if(minrating1 > minrating2){
            Minrating1.setTextColor(Color.parseColor("#b2fc58"));
            Minrating2.setTextColor(Color.parseColor("#e23b1d"));
        }
        else{
            Minrating2.setTextColor(Color.parseColor("#b2fc58"));
            Minrating1.setTextColor(Color.parseColor("#e23b1d"));
        }

    }
}
