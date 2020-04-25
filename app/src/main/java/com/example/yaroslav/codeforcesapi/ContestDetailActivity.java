package com.example.yaroslav.codeforcesapi;

import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.time.*;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import java.util.List;
import java.util.TimeZone;

public class ContestDetailActivity extends RecyclerView.Adapter<ContestDetailActivity.ViewHolder> {

    private List<Contest> contests;
    Context context;

    public ContestDetailActivity(Context applicationContext, List<Contest> contestArrayList){
        this.context = applicationContext;
        contests = contestArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_row_contest, parent, false);
        return new ContestDetailActivity.ViewHolder(view);
    }
    String id="";
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.contestname.setText(contests.get(i).getName());
        String st = "";
        int t = Integer.parseInt(contests.get(i).getDuration()),p;
        p=t/3600;
        if(p<10)
            st += "0";
        st += Integer.toString(p);
        st += ":";
        p =( t%3600)/60;
        if(p<10)
            st += "0";
        st += (p);
        viewHolder.duration.setText(st);
        SimpleDateFormat sdf = new SimpleDateFormat("d, MMMM, yyyy 'at' h:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        String date = sdf.format(Integer.parseInt(contests.get(i).getStartTime())*1000L);
        //id = Integer.parseInt(contests.get(i).getId());
        viewHolder.starttime.setText(date);

        viewHolder.item_div.setAnimation(AnimationUtils.loadAnimation(context, R.anim.block_item_anim));
    }
    private String link = "https://codeforces.com/contest/";
    @Override
    public int getItemCount() {
        return contests.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contestname, duration, starttime;
        RelativeLayout item_div;

        public ViewHolder(View view) {
            super(view);
            contestname = (TextView) view.findViewById(R.id.rcontestname);
            duration = (TextView) view.findViewById(R.id.rduration);
            starttime = (TextView) view.findViewById(R.id.rstarttime);
            item_div = view.findViewById(R.id.item_block);

            //on item click

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    id = contests.get(pos).getId();
                    link = link+id;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    Toast.makeText(context, "Link: "+link, Toast.LENGTH_SHORT).show();
                    context.startActivity(browserIntent);
                    link="https://codeforces.com/contest/";

                }

            });
        }
    }
}
