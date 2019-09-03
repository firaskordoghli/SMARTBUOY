package com.example.smartbuoy.DATA.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Models.Plan;
import com.example.smartbuoy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlanProfileAdapter extends RecyclerView.Adapter<PlanProfileAdapter.PlanViewHolder> {

    private List<Plan> mPlanList;

    public PlanProfileAdapter(List<Plan> mPlanList) {
        this.mPlanList = mPlanList;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_plan, parent, false);
        PlanViewHolder planViewHolder = new PlanViewHolder(view);
        return planViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        Plan plan = mPlanList.get(position);

        //Picasso.get().load(plan.getMainImage()).into(holder.planIv);

        holder.planPlageNameTextView.setText(plan.getNomPlage());
        holder.planeDateTextView.setText(plan.getDate().substring(0,10));
        holder.planCityTextView.setText(plan.getVillePlage());
    }

    @Override
    public int getItemCount() {
        try {
            return mPlanList.size();
        } catch (Exception e) {

            System.out.println("Error " + e.getMessage());
            return 0;

        }
    }


    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        public ImageView planIv;
        public TextView planeDateTextView, planPlageNameTextView, planCityTextView;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);

            planIv = itemView.findViewById(R.id.ivPlanProfile);
            planCityTextView = itemView.findViewById(R.id.tvCityPlan);
            planeDateTextView = itemView.findViewById(R.id.tvDatePlan);
            planPlageNameTextView = itemView.findViewById(R.id.tvNamePlagePlan);
        }
    }
}
