package com.minhaz.muhammad.drbatol.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minhaz.muhammad.drbatol.Private;
import com.minhaz.muhammad.drbatol.PrivateUpdateTaskActivity;
import com.minhaz.muhammad.drbatol.R;

import java.util.List;

public class PrivateAdapter extends RecyclerView.Adapter<PrivateAdapter.PrivateViewHolder> {

    private Context pCtx;
    List<Private> privateList;

    public PrivateAdapter(Context pCtx, List<Private> privateList) {
        this.pCtx = pCtx;
        this.privateList = privateList;
    }

    @NonNull
    @Override
    public PrivateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(pCtx).inflate(R.layout.private_row,parent,false);
        return new PrivateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrivateViewHolder holder, int position) {

        Private bPrivate = privateList.get(position);
        holder.txtFeel.setText(bPrivate.getFeel());
        holder.txtDescrip.setText(bPrivate.getDescrip());
        holder.txtSchdule.setText(bPrivate.getScheduleBy());

        if (bPrivate.isDone())
        {
            holder.txtViewStatus.setText("Completed");
            holder.txtViewStatus.setBackgroundColor(0xFF00BFA5);
        }
        else
        {
            holder.txtViewStatus.setText("Not completed");

        }
    }

    @Override
    public int getItemCount() {
        return privateList.size();
    }

    class PrivateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtViewStatus, txtFeel, txtDescrip, txtSchdule;

        public PrivateViewHolder(@NonNull View itemView) {

            super(itemView);

            txtViewStatus = itemView.findViewById(R.id.textViewPrivateStatus);
            txtFeel = itemView.findViewById(R.id.textViewFeel);
            txtDescrip = itemView.findViewById(R.id.textViewDescrip);
            txtSchdule = itemView.findViewById(R.id.textViewScheduleBy);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Private aPrivate = privateList.get(getAdapterPosition());
            Intent intent = new Intent(pCtx, PrivateUpdateTaskActivity.class);
            intent.putExtra("feel",aPrivate );
            pCtx.startActivity(intent);

        }
    }
}
