package com.example.smartbuoy.DATA.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomePlageAdapter extends RecyclerView.Adapter<HomePlageAdapter.HomePlageViewHolder> {
    private List<ItemHomePlage> mHomePlagesList;
    private OnItemClickListener mListener;

    public HomePlageAdapter(List<ItemHomePlage> itemHomePlages) {
        mHomePlagesList = itemHomePlages;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public HomePlageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_plage, parent, false);
        HomePlageViewHolder homePlageViewHolder = new HomePlageViewHolder(view, mListener);
        return homePlageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePlageViewHolder holder, int position) {
        ItemHomePlage itemHomePlage = mHomePlagesList.get(position);

        Picasso.get().load(itemHomePlage.getImage()).into(holder.imagePlage);
        holder.nomPlage.setText(itemHomePlage.getName());
        holder.villePlage.setText(itemHomePlage.getVille());
        holder.ratingPlage.setText(itemHomePlage.getRating());


    }

    @Override
    public int getItemCount() {
        return mHomePlagesList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class HomePlageViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagePlage;
        public TextView nomPlage, villePlage, ratingPlage;

        public HomePlageViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imagePlage = itemView.findViewById(R.id.imagePlage);
            nomPlage = itemView.findViewById(R.id.tvNomPlage);
            villePlage = itemView.findViewById(R.id.tvVillePlage);
            ratingPlage = itemView.findViewById(R.id.tvRatingPlage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}
