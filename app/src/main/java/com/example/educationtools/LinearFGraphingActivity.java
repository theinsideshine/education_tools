package com.example.educationtools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigatioview2.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.BreakIterator;
import java.text.DecimalFormat;

public class
LinearFGraphingActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series;
    private TextView tv1,tv2,tv3,tv4;
    private double x,y;                            //variables para graficar

    public static final int MAX_DATA_POINT = 500;  //Cantidad de puntos en x a calcular.
    public static final double RES_POINT = 0.1;    // Paso entre puntos x.
    public static final int INTERVAL_X = 10 ;     //  modulo del intervalo a partir del corte en X=0.
    public static final int INTERVAL_Y = 10 ;     //  modulo del intervalo a partir del corte en Y=0.

    // devuelve el formato con dos decimales
    DecimalFormat df = new DecimalFormat("#.##");

    private String convertToFormat(double value){

        return df.format(value);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_linearf_graphing );
        tv1 =findViewById( R.id.tvGraphingOrdinaryEcuation );
        tv2 =findViewById( R.id.tvGraphingGeneralEcuation );
        tv3 =findViewById( R.id.tvGraphingCanonicalEcuation );
        tv4 =findViewById( R.id.tvGraphingCeroEcuation );

        GraphView graph = (GraphView) findViewById( R.id.graph );
        LinearFData data = new LinearFData();

        //Recibe objeto
        Intent recieveGraphing = this.getIntent();
        data= recieveGraphing.getParcelableExtra("data");
        //revisar esta repetido en el scope del grafico


        tv1.setText( String.format( "Ec. ordinaria: y= %s x+ %s ",
                convertToFormat(data.getM()), convertToFormat(data.getY0()) ) );
        tv2.setText( String.format( "Ec. general: %s x+ %s y+ %s =0",
                convertToFormat(data.getA()), convertToFormat(data.getB()), convertToFormat(data.getB()) ) );
        tv3.setText( String.format( "Ec. canonica: x/%s + y/%s = 1 ",
                convertToFormat(data.getX0()), convertToFormat(data.getY0()) ) );
        tv4.setText( String.format( "Abscisa en 0: %s + Ordenada en 0: %s",
                convertToFormat(data.getX0()), convertToFormat(data.getY0()) ) );


       // tv4.setText("FlagY: " + data.isConstYFlag() +". FlagX: " + data.isConstXFlag());

        series = new LineGraphSeries<DataPoint>();








        if (data.isConstYFlag()) { //Recta constante en y
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(data.getX0()-INTERVAL_X , data.getY0()),
                    new DataPoint(data.getX0()+INTERVAL_X, data.getY0())
            });
            // Configura los ejes a partir de los cruces con cero -/+ INTERVAL modo fijo
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(data.getY0()-INTERVAL_Y);
            graph.getViewport().setMaxY(data.getY0()+INTERVAL_Y);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(data.getX0()-INTERVAL_X);
            graph.getViewport().setMaxX(data.getX0()+INTERVAL_X);
            graph.addSeries(series);
        }else if (data.isConstXFlag()){  //Recta constante en x no funciona bien
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {

                        new DataPoint(data.getX0() , data.getY0()-INTERVAL_Y),
                        new DataPoint(data.getX0() ,data.getY0()+ INTERVAL_Y)

                });
                // Configura los ejes a partir de los cruces con cero -/+ INTERVAL modo fijo
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(data.getY0()-INTERVAL_Y);
                graph.getViewport().setMaxY(data.getY0()+INTERVAL_Y);

                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(data.getX0()-INTERVAL_X);
                graph.getViewport().setMaxX(data.getX0()+INTERVAL_X);
                graph.addSeries(series);
        } else{ //recta con pendiente: resolver rando dibujo ,deberia ser corte con los ejes

            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {

                    new DataPoint(0, data.getY0()),
                    new DataPoint(data.getX0(), 0),

            });
                graph.addSeries( series );//LA GRAFICA PRIMERO PARA ENFOCAR  EL RANGO Y LUEGO ACTIVAR EL MODO DINAMICO

                // Configura los ejes modo dinamico
                graph.getViewport().setScalable(true);
                graph.getViewport().setScrollable(true);
                graph.getViewport().setScalableY(true);
                graph.getViewport().setScrollableY(false);


                PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, data.getY0()),
                        new DataPoint(data.getX0(), 0),

                });
                graph.addSeries(series2);
                series2.setColor(Color.RED);
                series2.setSize(10);
                series2.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series2, DataPointInterface dataPoint) {
                        Toast.makeText(LinearFGraphingActivity.this,
                                "El punto es: "+dataPoint, Toast.LENGTH_SHORT).show();
                    }
                });
            }
    }

// Vuelve al comienzo
    public void Back (View view){

        Intent back = new Intent(this, MainActivity.class);
        startActivity( back );
    }

    //controla el boton back
    @Override
    public void onBackPressed() {

    }
}




