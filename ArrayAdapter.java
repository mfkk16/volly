package com.example.calsys.api27nov;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.RecyclerViewHolder> {
    private List<Inventory> RVsList;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView iid, itemcode, item, unit;

        public RecyclerViewHolder(View view) {
            super(view);
            iid = view.findViewById(R.id.iid);
            itemcode = view.findViewById(R.id.itemcode);
            item = view.findViewById(R.id.item);
            unit = view.findViewById(R.id.unit);
        }
    }

    public ArrayAdapter(List<Inventory> RVsList) {
        this.RVsList = RVsList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Inventory e = RVsList.get(position);
        holder.iid.setText(e.iid());
        holder.itemcode.setText(e.itemcode());
        holder.item.setText(e.item());
        holder.unit.setText(e.unit());
    }

    @Override
    public int getItemCount() {
        return RVsList.size();
    }
}
