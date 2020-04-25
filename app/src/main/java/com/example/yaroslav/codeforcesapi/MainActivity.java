package com.example.yaroslav.codeforcesapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;
    String countrySort, citySort, searchHandle, Handle1c, Handle2c;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    final Context context = this;
    List<Item> users;
    List<Item> items;
    List<Item> usersс;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enable full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        setTitle("CodeForces");

        initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                users  = new ArrayList(items);
                countrySort = null;
                citySort = null;
                searchHandle = null;
                recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), users, countrySort, citySort, searchHandle));
                recyclerView.smoothScrollToPosition(0);
                swipeContainer.setRefreshing(false);
                pd.hide();
                Toast.makeText(MainActivity.this, "Codeforces Users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Codeforces Users...");
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
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<ItemResponse> call = apiService.getItems();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    items = response.body().getItems();
                    users  = new ArrayList(items);
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), users, countrySort, citySort,searchHandle));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        int id = menu.getItemId();

        if(id == R.id.sortButton) {

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.dialog_sort, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
            mDialogBuilder.setView(promptsView);
            final EditText Country = (EditText) promptsView.findViewById(R.id.country);
            final EditText City = (EditText) promptsView.findViewById(R.id.city);

            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    countrySort = Country.getText().toString();
                                    citySort = City.getText().toString();
                                    searchHandle = null;
                                    Toast.makeText(MainActivity.this, "Codeforces Users Found", Toast.LENGTH_SHORT).show();
                                    users  = new ArrayList(items);
                                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), users, countrySort, citySort, searchHandle));
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
            AlertDialog alertDialog = mDialogBuilder.create();
            alertDialog.show();
        }
        if(id == R.id.searchButton){
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.dialog_search, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
            mDialogBuilder.setView(promptsView);
            final EditText Hahdle = (EditText) promptsView.findViewById(R.id.search);

            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    searchHandle = Hahdle.getText().toString();
                                    countrySort = null;
                                    citySort = null;
                                    Toast.makeText(MainActivity.this, "Codeforces Users Found", Toast.LENGTH_SHORT).show();
                                    users  = new ArrayList(items);
                                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), users, countrySort, citySort, searchHandle));
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
            AlertDialog alertDialog = mDialogBuilder.create();
            alertDialog.show();
        }
        if (id == R.id.contestsButton){
            Intent intent1 = new Intent(this, ContestActivity.class);
            startActivity(intent1);
        }

        if(id == R.id.comparisonButton) {

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.dialog_comparison, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
            mDialogBuilder.setView(promptsView);
            final EditText Handle1 = (EditText) promptsView.findViewById(R.id.handle1);
            final EditText Handle2 = (EditText) promptsView.findViewById(R.id.handle2);

            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Handle1c = Handle1.getText().toString();
                                    Handle2c = Handle2.getText().toString();
                                    comparison();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
            AlertDialog alertDialog = mDialogBuilder.create();
            alertDialog.show();
        }

        return super.onOptionsItemSelected(menu);
    }

    private void comparison(){

        usersс= new ArrayList();

        for (int j = 0; j < items.size(); j++){
            if(items.get(j).getLogin().equals(Handle1c) || items.get(j).getLogin().equals(Handle2c)){
                usersс.add(items.get(j));
            }
        }
        if(usersс.size()!=2) return;
            Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, ComparisonActivity.class);

        intent.putExtra("login1", usersс.get(0).getLogin());
        intent.putExtra("rating1", usersс.get(0).getRating());
        intent.putExtra("country1", usersс.get(0).getCountry());
        intent.putExtra("city1", usersс.get(0).getCity());
        intent.putExtra("avatar_url1", "https:" + usersс.get(0).getAvatarUrl());
        intent.putExtra("organization1", usersс.get(0).getOrganization());
        intent.putExtra("maxRating1", usersс.get(0).getMaxRating());
        intent.putExtra("contribution1", usersс.get(0).getContribution());

        intent.putExtra("login2", usersс.get(1).getLogin());
        intent.putExtra("rating2", usersс.get(1).getRating());
        intent.putExtra("country2", usersс.get(1).getCountry());
        intent.putExtra("city2", usersс.get(1).getCity());
        intent.putExtra("avatar_url2", "https:" + usersс.get(1).getAvatarUrl());
        intent.putExtra("organization2", usersс.get(1).getOrganization());
        intent.putExtra("maxRating2", usersс.get(1).getMaxRating());
        intent.putExtra("contribution2", usersс.get(1).getContribution());


        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}