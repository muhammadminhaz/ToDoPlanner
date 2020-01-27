package com.minhaz.muhammad.drbatol.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minhaz.muhammad.drbatol.Fitness;
import com.minhaz.muhammad.drbatol.FitnessUpdateTaskActivity;
import com.minhaz.muhammad.drbatol.R;

import java.util.List;

public class FitnessAdapter extends RecyclerView.Adapter<FitnessAdapter.FitnessViewHolder> {

    private Context fCtx;
    List<Fitness> fitnessList;

    public FitnessAdapter(Context fCtx, List<Fitness> fitnessList) {
        this.fCtx = fCtx;
        this.fitnessList = fitnessList;
    }

    @NonNull
    @Override
    public FitnessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fCtx).inflate(R.layout.fitness_row,parent,false);
        return new FitnessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessViewHolder holder, int position) {

        Fitness fitness = fitnessList.get(position);
        holder.textFitnessWork.setText(fitness.getFitnessWork());
        holder.textFitnessDetails.setText(fitness.getFitnessDetails());
        holder.textFitnessSchedule.setText(fitness.getFitnessSchedule());

        if (fitness.isFit())
        {
            holder.textFitnessStatus.setText("Completed");
            holder.textFitnessStatus.setBackgroundColor(0xFF00BFA5);
        }
        else
        {
            holder.textFitnessStatus.setText("Not completed");
        }
    }

    @Override
    public int getItemCount() {
        return fitnessList.size();
    }

    class FitnessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textFitnessWork, textFitnessDetails, textFitnessSchedule, textFitnessStatus;
        public FitnessViewHolder(@NonNull View itemView) {
            super(itemView);

            textFitnessStatus = itemView.findViewById(R.id.textViewFitnessStatus);
            textFitnessWork = itemView.findViewById(R.id.textViewFitnessWork);
            textFitnessDetails = itemView.findViewById(R.id.textViewFitnessDetails);
            textFitnessSchedule = itemView.findViewById(R.id.textViewFitnessSchedule);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Fitness fitness = fitnessList.get(getAdapterPosition());
            Intent intent = new Intent(fCtx, FitnessUpdateTaskActivity.class);
            intent.putExtra("fitness",fitness);
            fCtx.startActivity(intent);
        }
    }
}
