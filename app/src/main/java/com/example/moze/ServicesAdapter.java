package com.example.moze;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private Context mCtx;
    private List<Service> serviceList;

    public ServicesAdapter(Context mCtx, List<Service> serviceList) {
        this.mCtx = mCtx;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_services, parent, false);
        return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {

        Service service = serviceList.get(position);

        holder.tvRPortfolio.setText(service.getPortfolio());
        holder.tvROccupation.setText(service.getOccupation());
        holder.tvRBusinessName.setText(service.getBusiness_name());
        holder.tvRDescription.setText(service.getDescription());
        holder.tvRLocation.setText(service.getLocation());
        holder.tvRWorking_hours.setText(service.getWorking_hours());
        holder.tvRPhone.setText(service.getPhone());
        holder.tvRCost.setText(service.getCost());

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    class ServicesViewHolder extends RecyclerView.ViewHolder{

        TextView tvRBusinessName, tvRDescription, tvRPortfolio, tvROccupation, tvRLocation, tvRWorking_hours, tvRPhone, tvRCost;

        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRPortfolio = itemView.findViewById(R.id.tvRPortfolio);
            tvROccupation = itemView.findViewById(R.id.tvROccupation);
            tvRBusinessName = itemView.findViewById(R.id.tvRBusinessName);
            tvRDescription = itemView.findViewById(R.id.tvRDescription);
            tvRLocation = itemView.findViewById(R.id.tvRLocation);
            tvRWorking_hours = itemView.findViewById(R.id.tvRWorking_hours);
            tvRPhone = itemView.findViewById(R.id.tvRPhone);
            tvRCost = itemView.findViewById(R.id.tvRCost);

        }
    }
}
