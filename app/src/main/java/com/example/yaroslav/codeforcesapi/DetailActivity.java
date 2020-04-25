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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    TextView Rating, Username, Organization, MaxRating, Contribution, Maxup, Maxdown, City, Country;
    ImageView imageView;
    TextView RatingText, UsernameText, OrganizationText, MaxRatingText, ContributionText, MaxupText, MaxdownText, CityText, CountryText;

    java.util.Map<String, Integer> Map;
    java.util.Map<String, Integer> MapLanguage;
    java.util.Map<String, Integer> MapLanguage1;
    java.util.Map<String, Integer> MapVerdict;
    java.util.Map<String, Integer> MapVerdict1;
    ArrayList<BarEntry> Data ;
    ArrayList<String> Labels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    BarChart barChart;
    PieChart pieChart;
    ArrayList<Entry> values;
    ArrayList<String> xVals;
    TagGroup mTagGroup;
    HashSet<String> myHashSet;
    PieChart pieChart1;
    ArrayList<Entry> values1;
    ArrayList<String> xVals1;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.user_image_header);
        Username = (TextView) findViewById(R.id.username);

        Rating = (TextView) findViewById(R.id.ratingl);
        Organization = (TextView) findViewById(R.id.organization1);
        Country = (TextView) findViewById(R.id.country1);
        City = (TextView) findViewById(R.id.city1);
        MaxRating = (TextView) findViewById(R.id.maxRating1);
        Contribution = (TextView) findViewById(R.id.contribution1);
        Maxup = (TextView) findViewById(R.id.maxup1);
        Maxdown = (TextView) findViewById(R.id.maxdown1);

        //Just text's

        RatingText = findViewById(R.id.rating1);
        OrganizationText = findViewById(R.id.organization);
        CountryText = findViewById(R.id.country);
        CityText = findViewById(R.id.city);
        MaxRatingText = findViewById(R.id.maxRating);
        ContributionText = findViewById(R.id.contribution);
        MaxupText = findViewById(R.id.maxup);
        MaxdownText = findViewById(R.id.maxdown);



        String username = getIntent().getExtras().getString("login");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String country = getIntent().getExtras().getString("country");
        String city = getIntent().getExtras().getString("city");
        String rating = getIntent().getExtras().getString("rating");
        String organization = getIntent().getExtras().getString("organization");
        String maxRating = getIntent().getExtras().getString("maxRating");
        String contribution = getIntent().getExtras().getString("contribution");




        Country.setText(country);
        City.setText(city);
        Rating.setText(rating);
        Organization.setText(organization);
        MaxRating.setText(maxRating);
        Contribution.setText(contribution);

        //Animations

        CountryText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset500));
        CityText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset500));
        Country.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset500));
        City.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset500));

        MaxRatingText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset700));
        RatingText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset700));
        Rating.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset700));
        MaxRating.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset700));

        OrganizationText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset900));
        ContributionText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset900));
        Organization.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset900));
        Contribution.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset900));

        MaxupText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset1100));
        MaxdownText.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset1100));
        Maxup.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset1100));
        Maxdown.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim_offset1100));




        Username.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                //.placeholder(R.drawable.load)
                .into(imageView);

        getSupportActionBar().setTitle("Profile");


        Map = new TreeMap<String, Integer>();
        barChart = (BarChart) findViewById(R.id.barchart);
        Data = new ArrayList<>();
        Labels = new ArrayList<String>();



        MapLanguage = new TreeMap<String, Integer>();
        MapLanguage1 = new TreeMap<String, Integer>();

        MapVerdict = new TreeMap<String, Integer>();
        MapVerdict1 = new TreeMap<String, Integer>();

        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        pieChart1 = (PieChart) findViewById(R.id.piechart1);
        pieChart1.setUsePercentValues(true);
        pieChart1.setExtraOffsets(5,10,5,5);
        pieChart1.setDragDecelerationFrictionCoef(0.95f);
        pieChart1.setDrawHoleEnabled(true);
        pieChart1.setHoleColor(Color.WHITE);
        pieChart1.setTransparentCircleRadius(61f);

        values = new ArrayList<>();
        xVals = new ArrayList<String>();

        values1 = new ArrayList<>();
        xVals1 = new ArrayList<String>();


        myHashSet = new HashSet<String>();


        Service_Submission apiService = Client.getClient().create(Service_Submission.class);
        Call<SubmissionList> call = apiService.getList(username);


        call.enqueue(new Callback<SubmissionList>() {
            @Override
            public void onResponse(Call<SubmissionList> call, Response<SubmissionList> response) {
                List<Submission> Submission = response.body().getSubmission();
                //Username.setText(Submission.get(0).getProblem().getIndex());


                for (int i = 0; i < Submission.size(); i++){
                    String level = Submission.get(i).getProblem().getIndex();
                    String language = Submission.get(i).getProgrammingLanguage();
                    String verdict = Submission.get(i).getVerdict();


                    if(MapLanguage.containsKey(language)){
                        int value = MapLanguage.get(language);
                        MapLanguage.put(language, value + 1);
                    } else MapLanguage.put(language, 1);

                    if(MapVerdict.containsKey(verdict)){
                        int value = MapVerdict.get(verdict);
                        MapVerdict.put(verdict, value + 1);
                    } else MapVerdict.put(verdict, 1);

                    if (level.length()==1 && (verdict).equals("OK")){
                        if(Map.containsKey(level)){
                            int value = Map.get(level);
                            Map.put(level, value + 1);
                        } else Map.put(level, 1);

                        myHashSet.addAll(Submission.get(i).getProblem().getTags());
                    }
                }

                int j=0;
                for(java.util.Map.Entry entry: Map.entrySet()){
                    Data.add(new BarEntry((int)entry.getValue(), j));
                    Labels.add((String) entry.getKey());
                    j++;

                }

                for(java.util.Map.Entry entry: MapLanguage.entrySet()){
                    float a = (int) entry.getValue() / (float) Submission.size();
                    if(a*100<3) continue;
                    MapLanguage1.put((String) entry.getKey(), (int)entry.getValue());

                }

                for(java.util.Map.Entry entry: MapVerdict.entrySet()){
                    float a = (int) entry.getValue() / (float) Submission.size();
                    if(a*100<3) continue;
                    MapVerdict1.put((String) entry.getKey(), (int)entry.getValue());

                }

                j=0;
                for(java.util.Map.Entry entry: MapLanguage1.entrySet()){
                    values.add(new Entry((int)entry.getValue(), j));
                    xVals.add((String) entry.getKey());
                    j++;

                }

                j=0;
                for(java.util.Map.Entry entry: MapVerdict1.entrySet()){
                    values1.add(new Entry((int)entry.getValue(), j));
                    xVals1.add((String) entry.getKey());
                    j++;

                }

                Ch();

            }

            @Override
            public void onFailure(Call<SubmissionList> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });

        Service_Rating apiService1 = Client.getClient().create(Service_Rating.class);
        Call<RatingChangeList> call1 = apiService1.getList(username);


        call1.enqueue(new Callback<RatingChangeList>() {
            @Override
            public void onResponse(Call<RatingChangeList> call, Response<RatingChangeList> response) {
                List<RatingChange> RatingChange = response.body().getRatingChange();
                int maxup=0,maxdown=0;
                for (int i = 0; i < RatingChange.size(); i++){
                    int oldr=Integer.parseInt(RatingChange.get(i).getOldRating());
                    int newr=Integer.parseInt(RatingChange.get(i).getNewRating());
                    if(newr>oldr) maxup=Math.max(maxup,newr-oldr);
                    if(newr<oldr) maxdown=Math.max(maxdown,oldr-newr);
                }
                ch1(maxup,maxdown);

            }

            @Override
            public void onFailure(Call<RatingChangeList> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });



    }

    private void Ch(){
        Bardataset = new BarDataSet(Data, "Task level");
        Bardataset.setColor(-1);
        BARDATA = new BarData(Labels, Bardataset);
        Bardataset.setValueTextColor(-1);
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(BARDATA);
        barChart.getXAxis().setTextColor(-1);
        barChart.getAxis(YAxis.AxisDependency.LEFT).setTextColor(-1);
        barChart.getAxis(YAxis.AxisDependency.RIGHT).setTextColor(-1);
        barChart.animateY(1000);
        barChart.setDescriptionColor(-1);
        barChart.getLegend().setTextColor(-1);

        //barChart.setDrawGridBackground(true);
        barChart.setDescription("Levels");

        barChart.setAnimation(AnimationUtils.loadAnimation(this, R.anim.block_item_anim));


        mTagGroup = (TagGroup) findViewById(R.id.tag_group);
        mTagGroup.setTags(myHashSet.toArray(new String[myHashSet.size()]));



        PieDataSet dataSet = new PieDataSet(values, "Election Results");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(xVals, dataSet);
        data.setValueTextSize(8f);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);
        pieChart.setHoleColor(Color.rgb(21,27,36));
        pieChart.animateY(1000);
        pieChart.setDescription("Programming languages");
        pieChart.setCenterTextColor(-1);
        pieChart.setDescriptionColor(-1);
        //pieChart.setDescriptionPosition(10000f, 1000f);
        pieChart.setDescriptionTextSize(15);
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setTextColor(Color.WHITE);


        PieDataSet dataSet1 = new PieDataSet(values1, "Election Results");
        dataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data1 = new PieData(xVals1, dataSet1);
        data1.setValueTextSize(8f);
        data1.setValueFormatter(new PercentFormatter());
        pieChart1.setData(data1);
        pieChart1.setDrawHoleEnabled(false);
        pieChart1.setDrawHoleEnabled(true);
        pieChart1.setTransparentCircleRadius(30f);
        pieChart1.setHoleRadius(30f);
        pieChart1.animateY(1000);
        pieChart1.setDescription("Verdict");
        pieChart1.setHoleColor(Color.rgb(21,27,36));
        pieChart1.setDescriptionTextSize(15);
        pieChart1.setDescriptionColor(-1);
        Legend p = pieChart1.getLegend();
        p.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        p.setTextColor(-1);



    }



    @Override
    public void onNothingSelected(){
        // do stuff
    }
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h){
        //Log.i("I clicked on", String.valueOf(e.getXIndex()) );
        List<Integer> l = new ArrayList<Integer>(MapLanguage1.values());
        //Toast.makeText(this,  Integer.toString( l.get(0)), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,  Integer.toString(l.get(Integer.parseInt(String.valueOf(e.getXIndex())))), Toast.LENGTH_SHORT).show();
    }

    private void ch1(int maxup,int maxdown){
        Maxup.setText(Integer.toString(maxup));
        Maxdown.setText("-"+Integer.toString(maxdown));
    }




    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }*/


   /* private Intent createShareForcastIntent(){
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer @" + username + ", " + link)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForcastIntent());
        return true;
    }*/
}
