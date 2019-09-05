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

public class FavorisAdapter extends RecyclerView.Adapter<FavorisAdapter.FavorisViewHolder> {
    private List<ItemHomePlage> favorisList;
    private OnItemClickListener mListener;

    public FavorisAdapter(List<ItemHomePlage> favorisList) {
        this.favorisList = favorisList;
    }

    public void setOnItemClickListener(HomePlageAdapter.OnItemClickListener listener) {
        mListener = (OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public FavorisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favoris_profile, parent, false);
        FavorisViewHolder favorisViewHolder = new FavorisViewHolder(view,mListener);
        return favorisViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavorisViewHolder holder, int position) {
        ItemHomePlage itemHomePlage = favorisList.get(position);

        Picasso.get().load(itemHomePlage.getImage()).into(holder.imagePlage);
        holder.nomPlage.setText(itemHomePlage.getName());
        holder.villePlage.setText(itemHomePlage.getVille());
    }

    @Override
    public int getItemCount() { return favorisList.size(); }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class FavorisViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePlage;
        private TextView nomPlage,villePlage;
        public FavorisViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imagePlage = itemView.findViewById(R.id.favorisImage);
            nomPlage = itemView.findViewById(R.id.favorisPlageName);
            villePlage = itemView.findViewById(R.id.favorisPlageRegion);

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
