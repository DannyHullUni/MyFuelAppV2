package com.example.myfuelapp;

import android.content.Context;
import android.nfc.Tag;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    private static final String TAG = "CarListAdapter";
    private final LayoutInflater mInflater;
    private List<Car> mCars;
    private OnItemClickListener listener;

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

            String mpg = "Average MPG - " + Double.toString(current.getCarMPG());
            String id = "#" + Integer.toString(current.getID());

            holder.carItemView.setText("Make - " + current.getCar());
            holder.carModelView.setText("Model - " + current.getCarModel());
            holder.carMPGView.setText(mpg);
            holder.carIDView.setText(id);

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
        private final TextView carModelView;
        private final TextView carMPGView;
        private final TextView carIDView;


        private CarViewHolder(View itemView) {
            super(itemView);
            carItemView = itemView.findViewById(R.id.textView);
            carModelView = itemView.findViewById(R.id.modelTextView);
            carMPGView = itemView.findViewById(R.id.mpgTextView);
            carIDView = itemView.findViewById(R.id.idTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.ItemClicked(mCars.get(position));}
                }
            });
        }
    }

    public interface OnItemClickListener {
        void ItemClicked (Car car);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Car getCarAtPosition (int position) {
        return mCars.get(position);
    }
}
