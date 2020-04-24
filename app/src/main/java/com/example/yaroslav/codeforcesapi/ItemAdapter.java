package com.example.yaroslav.codeforcesapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items1, items;
    private Context context;
    private String countrySort, citySort, searchHandle;

    private boolean checkCountry(String country){
        if(country == null || country.isEmpty())
            return true;
        if (countrySort.equals(country))
            return false;
        return true;
    }

    private boolean checkCity (String city){
        if(city == null || city.isEmpty())
            return true;
        if (citySort.equals(city)) {
            return false;
        }
        return true;
    }
    private boolean checkHandle (String handle){
        if(handle == null || handle.isEmpty())
            return true;
        if (searchHandle.equals(handle)) {
            return false;
        }
        return true;
    }


    public ItemAdapter(Context applicationContext, List<Item> itemArrayList, String co, String ci, String se) {
        this.context = applicationContext;
        if(items==null || items.isEmpty())
            items=itemArrayList;
        items1 = items;

        searchHandle = se;

        if (searchHandle != null && !searchHandle.isEmpty())
            for (int i = 0; i < items1.size(); i++) {
                if (checkHandle(items1.get(i).getLogin())) {
                    items1.remove(i);
                    i--;
                }
            }
        countrySort = co;
        if (countrySort != null && !countrySort.isEmpty()) {
            for (int i = 0; i < items1.size(); i++) {
                if (checkCountry(items1.get(i).getCountry())) {
                    items1.remove(i);
                    i--;
                }
            }
        }

        citySort = ci;
        if (citySort != null && !citySort.isEmpty()) {
            for (int i = 0; i < items1.size(); i++) {
                if (checkCity(items1.get(i).getCity())) {
                    items1.remove(i);
                    i--;
                }
            }
        }
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText((i + 1) + ". " + items1.get(i).getLogin());
        viewHolder.rating1.setText(items1.get(i).getRating());
        String photo = "https:" + items1.get(i).getAvatarUrl();
        Picasso.with(context)
                .load(photo)
                //.placeholder(R.drawable.load)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, rating1;
        private ImageView imageView;


        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            rating1 = (TextView) view.findViewById(R.id.rating1);
            imageView = (ImageView) view.findViewById(R.id.cover);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = items1.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", items1.get(pos).getLogin());
                        intent.putExtra("rating", items1.get(pos).getRating());
                        intent.putExtra("country", items1.get(pos).getCountry());
                        intent.putExtra("city", items1.get(pos).getCity());
                        intent.putExtra("avatar_url", "https:" + items1.get(pos).getAvatarUrl());
                        intent.putExtra("organization", items1.get(pos).getOrganization());
                        intent.putExtra("maxRating", items1.get(pos).getMaxRating());
                        intent.putExtra("contribution", items1.get(pos).getContribution());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
}
