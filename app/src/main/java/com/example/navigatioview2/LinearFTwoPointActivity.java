package com.example.navigatioview2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LinearFTwoPointActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_flinear_two_point );
    }

    public void Graphing (View view){

        Intent graphing = new Intent(this, Graphing.class);
        startActivity( graphing );
    }
}