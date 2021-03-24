package com.example.educationtools;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.navigatioview2.R;

public class LinearFTwoPointActivity extends AppCompatActivity  {

    private EditText et_X1,et_Y1,et_X2,et_Y2;
    private double X1;
    private double Y1;
    private double X2;
    private double Y2;
    private String strX1;
    private String strY1;
    private String strX2;
    private String strY2;

    private LinearFData data = new LinearFData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_linearf_two_point );
        et_X1 = findViewById( R.id.etxTwoPointInX1);
        et_Y1 = findViewById( R.id.etxTwoPointInY1);
        et_X2 = findViewById( R.id.etxTwoPointInX2);
        et_Y2 = findViewById( R.id.etxTwoPointInY2);

    }

    public void Graphing (View view){
        Calculate();
        Intent graphing = new Intent(this, LinearFGraphingActivity.class);
        graphing.putExtra("data", data); // manda el objeto data
        startActivity( graphing );
    }

    private void Calculate() {

        strX1 = et_X1.getText().toString();
        strY1 = et_Y1.getText().toString();
        strX2 = et_X2.getText().toString();
        strY2 = et_Y2.getText().toString();

        try {
            X1 = Double.parseDouble(strX1);
        }catch (NumberFormatException e) {
            //lat_string does not contain valid double value
        }
        try {
            Y1 = Double.parseDouble(strY1);
        }catch (NumberFormatException e) {
            //lat_string does not contain valid double value
        }
        try {
            X2 = Double.parseDouble(strX2);
        }catch (NumberFormatException e) {
            //lat_string does not contain valid double value
        }
        try {
            Y2 = Double.parseDouble(strY2);
        }catch (NumberFormatException e) {
            //lat_string does not contain valid double value
        }


       data.lfu_gph_calculate_from_ecuation_ordinary( X1,Y1,X2,Y2 );




    }


}

