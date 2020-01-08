package com.example.myfuelapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewCarActivity extends AppCompatActivity {
        public static final String EXTRA_REPLY = "com.example.android.myfuelapp.REPLY";
        public static final String EXTRA_MOD = "com.example.android.myfuelapp.MOD";
        public static final String EXTRA_MPG = "com.example.android.myfuelapp.MPG";
        public static final String EXTRA_ID = "com.example.android.myfuelapp.MODEL";

        private static final String TAG = "sendMPG";


        EditText mEditCarView;
        EditText mEditModelView;
        TextView mEditMPGView;
        EditText mEditIDView;

        private TextView mReturnText;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_car);
            mEditCarView = findViewById(R.id.edit_car);
            mEditModelView = findViewById(R.id.edit_car_model);
            mEditMPGView = findViewById(R.id.edit_car_MPG);
            mEditIDView = findViewById(R.id.edit_car_ID);

            mReturnText = findViewById(R.id.Output);

            final Button button = findViewById(R.id.button_save);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent replyIntent = new Intent(NewCarActivity.this, MainActivity.class);
                    if (TextUtils.isEmpty(mEditCarView.getText())) {
                        setResult(RESULT_CANCELED, replyIntent);
                    } else {
                        String car = mEditCarView.getText().toString();
                        String model = mEditModelView.getText().toString();

                        String mpg = mEditMPGView.getText().toString();
                        double newMPG = Double.parseDouble(mpg);

                        String ID = mEditIDView.getText().toString();
                        int newID = Integer.parseInt(ID);

                        replyIntent.putExtra(EXTRA_MOD, model);
                        replyIntent.putExtra(EXTRA_REPLY, car);
                        replyIntent.putExtra(EXTRA_MPG, newMPG);
                        replyIntent.putExtra(EXTRA_ID, newID);


                        setResult(RESULT_OK, replyIntent);
                    }
                    finish();
                }
            });

            final Button button_find_car = findViewById(R.id.button_find_car);
            button_find_car.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view) {
                    Intent replyIntent = new Intent();
                    if (TextUtils.isEmpty(mEditCarView.getText())) {
                        setResult(RESULT_CANCELED, replyIntent);
                    } else {
                        int ID = Integer.parseInt(mEditIDView.getText().toString());



                        //replyIntent.putExtra(EXTRA_REPLY, car);
                        //replyIntent.putExtra(EXTRA_MODEL, model);
                        //replyIntent.putExtra(EXTRA_MPG, mpg);
                        replyIntent.putExtra(EXTRA_ID, ID);

                        setResult(RESULT_OK, replyIntent);
                    }
                }
            });
        }
            public void fetchData(View view) {

                String sendMPG = mReturnText.getText().toString();
                TextView test123 = findViewById(R.id.edit_car_MPG);
                new GetCar(mReturnText,test123).execute(sendMPG);

                mReturnText.setText(sendMPG);






    }
}



