package com.mad.exercise5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

    private List<Train> mTrainList;
    private Context mContext;

    public TrainAdapter(Context context, ArrayList<Train> trains) {
        this.mTrainList = trains;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Train train = mTrainList.get(position);
        holder.platform.setText(train.getPlatform());
        holder.arrival.setText(train.getArrivalTime() + " " + mContext.getString(R.string.mins));

        if (train.getStatus().equals(mContext.getString(R.string.late))) {
            holder.status.setTextColor(Color.RED);
        }
        holder.status.setText(train.getStatus());
        holder.destination.setText(train.getDestination());
        holder.destinationTime.setText(train.getDestinationTime());
    }

    @Override
    public int getItemCount() {
        return mTrainList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView platform, arrival, status, destination, destinationTime;

        public ViewHolder(View itemView) {
            super(itemView);
            platform = (TextView) itemView.findViewById(R.id.platform_tv);
            arrival = (TextView) itemView.findViewById(R.id.arrival_time_tv);
            status = (TextView) itemView.findViewById(R.id.status_tv);
            destination = (TextView) itemView.findViewById(R.id.destination_name_tv);
            destinationTime = (TextView) itemView.findViewById(R.id.destination_time_tv);
        }
    }
}
