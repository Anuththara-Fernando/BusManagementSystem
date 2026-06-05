package com.smartbus.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {

    List<Bus> busList;

    public BusAdapter(List<Bus> busList) {
        this.busList = busList;
    }

    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bus_item, parent, false);

        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull BusViewHolder holder,
            int position) {

        Bus bus = busList.get(position);

        holder.txtBusName.setText(bus.getBusName());

        holder.txtRoute.setText(
                bus.getCity() + " → " + bus.getDestination()
        );

        holder.txtStatus.setText(
                "Status: " + bus.getStatus()
        );

        holder.txtEta.setText(
                "ETA: " + bus.getEta()
        );
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public static class BusViewHolder extends RecyclerView.ViewHolder {

        TextView txtBusName;
        TextView txtRoute;
        TextView txtStatus;
        TextView txtEta;

        public BusViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBusName = itemView.findViewById(R.id.txtBusName);
            txtRoute = itemView.findViewById(R.id.txtRoute);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtEta = itemView.findViewById(R.id.txtEta);
        }
    }
}