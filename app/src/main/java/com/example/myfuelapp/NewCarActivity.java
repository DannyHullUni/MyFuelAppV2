package com.example.myfuelapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewCarActivity extends AppCompatActivity {
        public static final String EXTRA_REPLY = "com.example.android.myfuelapp.REPLY";

        private EditText mEditCarView;
        private EditText mEditMakeView;
        private EditText mEditModelView;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_car);
            mEditCarView = findViewById(R.id.edit_car);
            //mEditModelView = findViewById(R.id.edit_car_model);


            final Button button = findViewById(R.id.button_save);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent replyIntent = new Intent();
                    if (TextUtils.isEmpty(mEditCarView.getText())) {
                        setResult(RESULT_CANCELED, replyIntent);
                    } else {
                        String man = mEditCarView.getText().toString();
                        //String model = mEditModelView.getText().toString();

                        replyIntent.putExtra(EXTRA_REPLY, man);
                        //replyIntent.putExtra(EXTRA_REPLY, model);
                        setResult(RESULT_OK, replyIntent);
                    }
                    finish();
                }
            });
        }
}



