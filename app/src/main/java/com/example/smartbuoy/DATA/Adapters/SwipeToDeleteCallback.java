package com.example.smartbuoy.DATA.Adapters;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.R;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private PlanProfileAdapter mAdapter;
    private Drawable icon;
    private final ColorDrawable background;

    public SwipeToDeleteCallback(PlanProfileAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;

        //icon = ContextCompat.getDrawable(mAdapter.getContext(),
          //      R.drawable.ic_favoris);
        background = new ColorDrawable(Color.RED);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        //mAdapter.deleteItem(position);
    }
}