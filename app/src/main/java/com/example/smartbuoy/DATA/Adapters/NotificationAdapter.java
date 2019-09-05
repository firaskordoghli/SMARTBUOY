package com.example.smartbuoy.DATA.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Models.Notification;
import com.example.smartbuoy.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<Notification> mListe;

    public NotificationAdapter(List<Notification> mListe) {
        this.mListe = mListe;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
        NotificationViewHolder notificationViewHolder = new NotificationViewHolder(view);
        return notificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = mListe.get(position);
        holder.description.setText(notification.getDescription());
    }

    @Override
    public int getItemCount() { return mListe.size(); }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.tvNotification);
            System.out.println(description);
        }
    }
}
