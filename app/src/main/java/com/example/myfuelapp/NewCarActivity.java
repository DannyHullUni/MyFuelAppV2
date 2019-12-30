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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public class NewWordActivity extends AppCompatActivity {
        public static final String EXTRA_REPLY =
                "com.example.android.roomwordssample.REPLY";

        private EditText mEditCarView;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_car);
            mEditCarView = findViewById(R.id.edit_car);

            final Button button = findViewById(R.id.button_save);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent replyIntent = new Intent();
                    if (TextUtils.isEmpty(mEditCarView.getText())) {
                        setResult(RESULT_CANCELED, replyIntent);
                    } else {
                        String word = mEditCarView.getText().toString();
                        replyIntent.putExtra(EXTRA_REPLY, word);
                        setResult(RESULT_OK, replyIntent);
                    }
                    finish();
                }
            });
        }
    }
}



