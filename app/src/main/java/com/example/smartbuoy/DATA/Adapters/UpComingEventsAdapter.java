package com.example.smartbuoy.DATA.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpComingEventsAdapter extends RecyclerView.Adapter<UpComingEventsAdapter.EventViewHolder> {

    private List<Event> mEventList;

    public UpComingEventsAdapter(List<Event> mEventList) {
        this.mEventList = mEventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = mEventList.get(position);
        Picasso.get().load(event.getImage()).into(holder.eventImageView);
        holder.eventTitle.setText(event.getTitre());
        int particip = event.getParticipants().size();
        holder.eventNumber.setText(String.valueOf(particip));
        holder.eventDate.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventImageView;
        public TextView eventTitle, eventNumber, eventDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.evenementTitle);
            eventNumber = itemView.findViewById(R.id.evenementNumber);
            eventDate = itemView.findViewById(R.id.evenementDate);
            eventImageView = itemView.findViewById(R.id.evenementImage);

        }
    }
}