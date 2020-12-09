package com.example.shadesfinal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ShadeRecyclerViewAdapter extends RecyclerView.Adapter<com.example.shadesfinal.ShadeRecyclerViewAdapter.ViewHolder> {

    private final List<com.example.shadesfinal.Shade> mValues;
    private Context context;

    public ShadeRecyclerViewAdapter(List<com.example.shadesfinal.Shade> mValues, Context context) {
        this.mValues = mValues;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shade_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mShade = mValues.get(position);
        holder.mShadeTextView.setText(holder.mShade.getName());
        holder.mShadeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    Intent intent = new Intent(context, com.example.shadesfinal.InformationActivity.class);
                    intent.putExtra("shade_detail", holder.mShade.getDescription());
                    context.startActivity(intent);
                }
                else {
                    TextView shadeDetailTV = ((com.example.shadesfinal.MainActivity)context).findViewById(R.id.shade_detail_TV);
                    shadeDetailTV.setText(holder.mShade.getDescription());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mShadeTextView;
        public com.example.shadesfinal.Shade mShade;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mShadeTextView = (TextView) view.findViewById(R.id.shade_name_itemTV);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mShadeTextView.getText() + "'";
        }
    }
}