package com.example.moze.Services.ViewServices;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moze.PayPal.PayPalPayments;
import com.example.moze.R;
import com.example.moze.Services.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private Context mCtx;
    private List<Service> serviceList = new ArrayList<>();

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
        holder.tvRSocialLink.setText(service.getSocial_link());
        holder.tvRBusinessName.setText(service.getBusiness_name());
        holder.tvRDescription.setText(service.getDescription());
        holder.tvRLocation.setText(service.getLocation());
        holder.tvRWorking_hours.setText(service.getWorking_hours());
        holder.tvRPhone.setText(service.getPhone());
        holder.tvRCost.setText(service.getCost());
        holder.btnRCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, PayPalPayments.class);
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return serviceList.size();
    }

    class ServicesViewHolder extends RecyclerView.ViewHolder {

        TextView tvRBusinessName, tvRDescription, tvRPortfolio, tvROccupation, tvRSocialLink, tvRLocation, tvRWorking_hours, tvRPhone, tvRCost;
        Button btnRCheckout;

        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRPortfolio = itemView.findViewById(R.id.tvRPortfolio);
            tvROccupation = itemView.findViewById(R.id.tvROccupation);
            tvRBusinessName = itemView.findViewById(R.id.tvRBusinessName);
            tvRSocialLink = itemView.findViewById(R.id.tvRSocialLink);
            tvRDescription = itemView.findViewById(R.id.tvRDescription);
            tvRLocation = itemView.findViewById(R.id.tvRLocation);
            tvRWorking_hours = itemView.findViewById(R.id.tvRWorking_hours);
            tvRPhone = itemView.findViewById(R.id.tvRPhone);
            tvRCost = itemView.findViewById(R.id.tvRCost);
            btnRCheckout = itemView.findViewById(R.id.btnRCheckout);


        }
    }

}
