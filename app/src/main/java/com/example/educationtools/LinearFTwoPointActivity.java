package com.example.educationtools;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigatioview2.R;

public class LinearFTwoPointActivity extends AppCompatActivity  {

    //Variable para ingreso de puntos
    private EditText et_X1,et_Y1,et_X2,et_Y2;
    private double X1;
    private double Y1;
    private double X2;
    private double Y2;

    //Codigos de error.
    private final static boolean OP_OK = true;
    private final static boolean OP_ERROR = false;

    private final LinearFData data = new LinearFData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_linearf_two_point );
        et_X1 = findViewById( R.id.etxTwoPointInX1);
        et_Y1 = findViewById( R.id.etxTwoPointInY1);
        et_X2 = findViewById( R.id.etxTwoPointInX2);
        et_Y2 = findViewById( R.id.etxTwoPointInY2);

    }

    public void Graphing(View view){

        if(Calculate()){
            Intent graphing = new Intent(this, LinearFGraphingActivity.class);
            graphing.putExtra("data", data); // Manda el objeto data
            startActivity( graphing );
        }

    }

    //Calculo la ordenada en el origen y la pendiente a partir de 2 puntos.

    private boolean Calculate() {

        String strX1 = et_X1.getText().toString();
        String strY1 = et_Y1.getText().toString();
        String strX2 = et_X2.getText().toString();
        String strY2 = et_Y2.getText().toString();

        // Chequea que los datos esten ingresados.

        if (  strX1.equals("") || strY1.equals("") || strX2.equals("") || strY2.equals("") ){
            Toast toast = Toast.makeText(this, "Complete los campos ", Toast.LENGTH_LONG);
            toast.show();
            return OP_ERROR;
        }

        // Convierte de string a double.
        try {
            X1 = Double.parseDouble( strX1 );
        }catch (NumberFormatException e) {
            Toast toast = Toast.makeText(this, "Error parse Double ", Toast.LENGTH_SHORT);
            toast.show();
        }
        try {
            Y1 = Double.parseDouble( strY1 );
        }catch (NumberFormatException e) {
            Toast toast = Toast.makeText(this, "Error parse Double ", Toast.LENGTH_SHORT);
            toast.show();
        }
        try {
            X2 = Double.parseDouble( strX2 );
        }catch (NumberFormatException e) {
            Toast toast = Toast.makeText(this, "Error parse Double ", Toast.LENGTH_SHORT);
            toast.show();
        }
        try {
            Y2 = Double.parseDouble( strY2 );
        }catch (NumberFormatException e) {
            Toast toast = Toast.makeText(this, "Error parse Double ", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Calcula m y b.
       if(data.lfu_gph_calculate_from_ecuation_ordinary( X1,Y1,X2,Y2 )) {
           return OP_OK;
       }else{
           Toast toast = Toast.makeText(this, "Ingreso el mismo punto ", Toast.LENGTH_SHORT);
           toast.show();
           return OP_ERROR;
       }

    }

    //controla el boton back
    @Override
    public void onBackPressed() {

    }

}

