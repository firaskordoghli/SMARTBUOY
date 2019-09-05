package com.example.smartbuoy.DATA;

import androidx.recyclerview.widget.RecyclerView;

public interface RecyclerItemTouchHelperListener {
    void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
