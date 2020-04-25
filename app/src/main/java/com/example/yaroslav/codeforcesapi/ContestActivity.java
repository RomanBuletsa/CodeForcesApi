package com.example.yaroslav.codeforcesapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestActivity extends AppCompatActivity {

    ProgressDialog pd;
    private RecyclerView recyclerView;
    List<Contest> contests;
    TextView Disconnected;
    private SwipeRefreshLayout swipeContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_contest);
        getSupportActionBar().setTitle("Contests");
        initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                recyclerView.setAdapter(new ContestDetailActivity(getApplicationContext(), contests));
                recyclerView.smoothScrollToPosition(0);
                swipeContainer.setRefreshing(false);
                pd.hide();
                Toast.makeText(ContestActivity.this, "Codeforces Users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Codeforces Contests...");
        pd.setCancelable(false);
        pd.show();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON(){
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try{
            Client Client = new Client();
            Service_Contest apiService =
                    Client.getClient().create(Service_Contest.class);
            Call<ContestResponse> call = apiService.getContests();
            call.enqueue(new Callback<ContestResponse>() {
                @Override
                public void onResponse(Call<ContestResponse> call, Response<ContestResponse> response) {
                    contests = response.body().getContests();
                    recyclerView.setAdapter(new ContestDetailActivity(getApplicationContext(), contests));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ContestResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(ContestActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
