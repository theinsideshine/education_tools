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

public class
LinearFGraphingActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series;
    private TextView tv1,tv2,tv3,tv4;
    private double x,y;

    public static final int MAX_DATA_POINT = 500;  //Cantidad de puntos en x a calcular.
    public static final double RES_POINT = 0.1;    // Paso entre puntos x.
    public static final int INTERVAL_X = 10 ;     //  modulo del intervalo a partir del corte en X=0.
    public static final int INTERVAL_Y = 10 ;     //  modulo del intervalo a partir del corte en Y=0.



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




        tv1.setText("Ec. ordinaria: y= "+ data.getM() +" x+ "+  data.getY0() );
        tv2.setText("Ec. general: "+ data.getA() +"  x+ "+ data.getB()+" y+" + data.getC() +" =0 ");
        tv3.setText("Ec. canonica: x/"+ data.getX0() +" +y/" + data.getY0()+ " = 1 ");
        //tv4.setText("Abcisa en 0: " + data.getX0() +". Ordenada en 0: " + data.getY0());
        tv4.setText("FlagY: " + data.isConstYFlag() +". FlagX: " + data.isConstXFlag());

        series = new LineGraphSeries<DataPoint>();

        if (data.isConstYFlag()) { //Recta constante en y
            x = data.getX0()-INTERVAL_X; //Coordenada x para empezar a graficar//Recta constante en y
            for (int i = 0; i < MAX_DATA_POINT; i++) {
                x = x + RES_POINT;
                y = data.getY0();
                series.appendData( new DataPoint( x, y ), true, MAX_DATA_POINT );
            }
        }else if (data.isConstXFlag()){  //Recta constante en x no funciona
                  y = - INTERVAL_Y;
                  for (int i = 0; i < MAX_DATA_POINT; i++){
                      y= y + (RES_POINT*10);
                      series.appendData(new DataPoint( data.getX0(), y ),true,MAX_DATA_POINT);
                      }


        } else{ //recta con pendiente
            x = data.getX0()-INTERVAL_X;
            for (int i = 0; i < MAX_DATA_POINT; i++){
                    x = x +RES_POINT;
                    y= data.getM() * x + data.getX0();
                    series.appendData(new DataPoint( x, y ),true,MAX_DATA_POINT);
                }
            }


        // Configura los ejes a partir de los cruces con cero - INTERVAL
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(data.getY0()-INTERVAL_Y);
        graph.getViewport().setMaxY(data.getY0()+INTERVAL_Y);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(data.getX0()-INTERVAL_X);
        graph.getViewport().setMaxX(data.getX0()+INTERVAL_X);

        // enable scaling and scrolling
        //graph.getViewport().setScalable(true);
       // graph.getViewport().setScalableY(true);
        graph.addSeries( series );
        /*
        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(data.getY0(),0 ),
                new DataPoint(0, data.getX0()),

        });
        graph.addSeries(series2);
        series2.setColor( Color.RED);
        series2.setSize(10);

        series2.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series2, DataPointInterface dataPoint) {
                Toast.makeText(
                        LinearFGraphingActivity.this, "Punto seleccionado "+dataPoint, Toast.LENGTH_LONG).show();
            }
        });*/
    }


// Vuelve al comienzo
    public void Back (View view){

        Intent back = new Intent(this, MainActivity.class);
        startActivity( back );
    }
}


