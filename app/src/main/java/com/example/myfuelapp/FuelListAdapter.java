package com.example.myfuelapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class FuelListAdapter extends RecyclerView.Adapter<FuelListAdapter.FuelViewHolder> {

    private final LayoutInflater mInflater;
    private List<Fuel> mFuel;

    FuelListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public FuelListAdapter.FuelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_fuel, parent, false);
        return new FuelViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FuelListAdapter.FuelViewHolder holder, int position) {
        if (mFuel != null) {

            Fuel current = mFuel.get(position);

            //String mpg = "Average MPG - " + Double.toString(current.getCarMPG());
            String fuelPrice = "Fuel Price per L - £" + Double.toString(current.getMFuelPrice());
            String fuelLitres = "Litres Dispensed - " + Double.toString(current.getMFuelLitres()) + " Litres";
            String Odometer = "Odometer - " + Integer.toString(current.getMOdometer()) + " Miles";

            holder.odometerView.setText(Odometer);
            holder.fuelPriceView.setText(fuelPrice);
            holder.fuelLitresView.setText(fuelLitres);

            //48.8 miles to the gallon, gallon = 4 litres so divide 48.8 by 4 to get litres
            //COST PER MILE
            double fuelprice = (current.getMFuelPrice()); //get the fuel price per Litre
            String newFuelprice = Double.toString(fuelprice); //put it into a double
            double finalFuelPrice = Double.parseDouble(newFuelprice);
            double totalCostPerMile = finalFuelPrice / 48.8; //work out how much £ per mile
            String newTotalCostPerMile = "Cost Per Mile - £" + Double.toString(totalCostPerMile);

            holder.fuelMilesView.setText(newTotalCostPerMile);

            //COST PER LITRE
            double CPLPrice = current.getMFuelPrice();
            double CPLLitres = current.getMFuelLitres();
            double sum = CPLPrice / CPLLitres;
            String newSum = "Cost Per Litre - £" + Double.toString(sum);

            holder.fuelCostPerLitreView.setText(newSum);

            //MILES PER LITRE
            double gallonToLitre = 0.21;
            double MPLsum = 48.8 * gallonToLitre;
            String newMPLsum = "Miles per Litre - " + MPLsum + " Miles";

            holder.fuelMilesPerLitreView.setText(newMPLsum);

            //KILO PER LITRE
            String newKPLsum = "Kilometers Per Litre - " + MPLsum * 1.60 + " Km";

            holder.fuelKilosPerLitreView.setText(newKPLsum);



        } else {
            // Covers the case of data not being ready yet.
            holder.odometerView.setText("No Reading Recorded");
        }
    }

    void setFuel(List<Fuel> Fuel) {
        mFuel = Fuel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mFuel != null) {
            return mFuel.size();
        }
        else return 0;
    }

    class FuelViewHolder extends RecyclerView.ViewHolder {

        private final TextView odometerView;
        private final TextView fuelPriceView;
        private final TextView fuelLitresView;
        private final TextView fuelMilesView;
        private final TextView fuelCostPerLitreView;
        private final TextView fuelMilesPerLitreView;
        private final TextView fuelKilosPerLitreView;


        private FuelViewHolder(View itemView) {
            super(itemView);

            odometerView = itemView.findViewById(R.id.odometer_reading);
            fuelPriceView = itemView.findViewById(R.id.fuel_price);
            fuelLitresView = itemView.findViewById(R.id.fuel_litres);
            fuelMilesView = itemView.findViewById(R.id.fuel_miles);
            fuelCostPerLitreView = itemView.findViewById(R.id.fuel_cost_per_litre);
            fuelMilesPerLitreView = itemView.findViewById(R.id.fuel_miles_per_litre);
            fuelKilosPerLitreView = itemView.findViewById(R.id.fuel_kilo_per_litre);

        }
    }

    public Fuel getFuelAtPosition (int position) {
        return mFuel.get(position);
    }
}

