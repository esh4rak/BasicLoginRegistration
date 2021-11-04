package com.eshrak.basicloginregistration.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eshrak.basicloginregistration.R;
import com.eshrak.basicloginregistration.models.ShowItem;
import com.google.android.material.card.MaterialCardView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {

    private ArrayList<ShowItem> sShowItems;
    private OnItemClickListener sListener;
    private Context context;


    public void setOnItemClickListener(OnItemClickListener listener) {
        sListener = listener;
    }


    public ShowAdapter(ArrayList<ShowItem> showItems, Context context) {
        sShowItems = showItems;
        this.context = context;
    }


    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false);
        return new ShowViewHolder(view, sListener);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {

        ShowItem currentItem = sShowItems.get(position);


        if (currentItem.getShowName() != null && !currentItem.getShowName().isEmpty()) {
            holder.showNameTV.setText(currentItem.getShowName());
        } else {
            holder.showNameTV.setText("N/A");
        }

        if (currentItem.getType() != null && !currentItem.getType().isEmpty()) {
            holder.showTypeTV.setText(currentItem.getType());
        } else {
            holder.showTypeTV.setText("N/A");
        }

        if (currentItem.getLanguage() != null && !currentItem.getLanguage().isEmpty()) {
            holder.languageTV.setText(currentItem.getLanguage());
        } else {
            holder.languageTV.setText("N/A");
        }


        if (currentItem.getPremiered() != null && !currentItem.getPremiered().isEmpty()) {
            holder.premieredTV.setText("Premiered: " + currentItem.getPremiered());
        } else {
            holder.premieredTV.setText("Premiered: N/A");
        }


        if (currentItem.getRating() != null && !currentItem.getRating().isEmpty()) {
            holder.ratingTV.setText("Rating: " + currentItem.getRating());
        } else {
            holder.ratingTV.setText("Rating: N/A");
        }


        URL url = null;
        try {
            url = new URL(currentItem.getShowImage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        Glide.with(context)
                .load(url)
                .error(R.drawable.ic_image_not_found)
                .centerCrop()
                .into(holder.shoImageView);

    }

    @Override
    public int getItemCount() {
        return sShowItems.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void getShow(ArrayList<ShowItem> items) {
        sShowItems.clear();
        sShowItems.addAll(items);
        notifyDataSetChanged();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<ShowItem> items) {
        sShowItems.clear();
        sShowItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(int position, ShowItem viewModel) {
        sShowItems.add(position, viewModel);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        sShowItems.remove(position);
        notifyItemRemoved(position);
    }


    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }


    public static class ShowViewHolder extends RecyclerView.ViewHolder {


        MaterialCardView parentView;
        ImageView shoImageView;
        TextView showNameTV;
        TextView showTypeTV;
        TextView languageTV;
        TextView premieredTV;
        TextView ratingTV;


        public ShowViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);


            parentView = itemView.findViewById(R.id.itemShow_CardView);
            shoImageView = itemView.findViewById(R.id.itemShow_showImage);
            showNameTV = itemView.findViewById(R.id.itemShow_showName);
            showTypeTV = itemView.findViewById(R.id.itemShow_type);
            languageTV = itemView.findViewById(R.id.itemShow_language);
            premieredTV = itemView.findViewById(R.id.itemShow_premiered);
            ratingTV = itemView.findViewById(R.id.itemShow_rating);

            parentView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(pos, v);
                }
            });

        }
    }
}
