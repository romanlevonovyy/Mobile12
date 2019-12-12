package com.dovhan.application.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dovhan.application.R;
import com.dovhan.application.activities.ItemDetailsActivity;
import com.dovhan.application.entities.FoodMachine;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<FoodMachine> foodMachines;
    private Context mContext;

    public ItemAdapter(Context context, List<FoodMachine> foodMachines) {
        this.mContext = context;
        this.foodMachines = foodMachines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                         final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,
                                 final int position) {
        holder.name.setText(foodMachines.get(position).getName());
        holder.company.setText(foodMachines.get(position).getCompany());
        holder.goods.setText(foodMachines.get(position).getGood());
        holder.address.setText(foodMachines.get(position).getAddress());
        Picasso.get()
                .load(foodMachines.get(position).getPicture())
                .placeholder(R.drawable.vending_placeholder)
                .into(holder.imageUrl);
        holder.parentLayout.setOnClickListener(view -> openItemDetails(position));
    }

    private void openItemDetails(int position) {
        Intent intent = new Intent(mContext, ItemDetailsActivity.class);
        intent.putExtra("vending_name", foodMachines.get(position).getName());
        intent.putExtra("vending_company", foodMachines.get(position).getCompany());
        intent.putExtra("vending_goods", foodMachines.get(position).getGood());
        intent.putExtra("vending_address", foodMachines.get(position).getAddress());
        intent.putExtra("vending_img_url", foodMachines.get(position).getPicture());

        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return foodMachines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView company;
        private TextView goods;
        private TextView address;
        private ImageView imageUrl;
        private ConstraintLayout parentLayout;

        ViewHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            company = itemView.findViewById(R.id.item_company);
            goods = itemView.findViewById(R.id.item_goods);
            address = itemView.findViewById(R.id.item_address);
            imageUrl = itemView.findViewById(R.id.item_img);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
