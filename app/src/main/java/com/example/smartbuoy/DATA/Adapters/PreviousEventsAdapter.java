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

public class PreviousEventsAdapter extends RecyclerView.Adapter<PreviousEventsAdapter.EventViewHolder> {

    private List<Event> mEventList;
    private UpComingEventsAdapter.OnItemClickListener mListener;


    public PreviousEventsAdapter(List<Event> mEventList) {
        this.mEventList = mEventList;
    }

    public void setOnItemClickListener(UpComingEventsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_events, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view, mListener);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = mEventList.get(position);
        Picasso.get().load(event.getImage()).into(holder.eventImageView);
        holder.eventTitle.setText(event.getTitre());
        int particip = event.getParticipants().size();
        holder.eventLocation.setText(event.getPlage());
        holder.eventType.setText(event.getType());
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public ImageView eventImageView;
        public TextView eventTitle, eventLocation, eventType;

        public EventViewHolder(@NonNull View itemView, final UpComingEventsAdapter.OnItemClickListener listener) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.evenementTitle);
            eventLocation = itemView.findViewById(R.id.evenementNumber);
            eventType = itemView.findViewById(R.id.evenementDate);
            eventImageView = itemView.findViewById(R.id.evenementImage);

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