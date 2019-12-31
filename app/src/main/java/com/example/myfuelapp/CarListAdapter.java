package com.example.myfuelapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    private final LayoutInflater mInflater;
    private List<Car> mCars;

    CarListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public CarListAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CarViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarListAdapter.CarViewHolder holder, int position) {
        if (mCars != null) {
            Car current = mCars.get(position);
            holder.carItemView.setText(current.getCar());
        } else {
            // Covers the case of data not being ready yet.
            holder.carItemView.setText("No Car");
        }
    }

    void setCars(List<Car> cars) {
        mCars = cars;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCars != null) {
            return mCars.size();
        }
        else return 0;
    }

    class CarViewHolder extends RecyclerView.ViewHolder {
        private final TextView carItemView;

        private CarViewHolder(View itemView) {
            super(itemView);
            carItemView = itemView.findViewById(R.id.textView);
        }
    }

    public Car getCarAtPosition (int position) {
        return mCars.get(position);
    }
}
