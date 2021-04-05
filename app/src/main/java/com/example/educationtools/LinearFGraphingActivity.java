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
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.DecimalFormat;

public class
LinearFGraphingActivity extends AppCompatActivity {

    private static DataPoint[] points = new DataPoint[ 1];

    private static LinearFData data = new LinearFData();

    public static final int INTERVAL_X = 10 ;     //  modulo del intervalo a partir del corte en X=0.
    public static final int INTERVAL_Y = 10 ;     //  modulo del intervalo a partir del corte en Y=0.

    // devuelve el formato con dos decimales
    final DecimalFormat df = new DecimalFormat("#.##");


    private String convertToFormat(double value){

        return df.format(value);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_linearf_graphing );
        TextView tv1 = findViewById( R.id.tvGraphingOrdinaryEcuation );
        TextView tv2 = findViewById( R.id.tvGraphingGeneralEcuation );
        TextView tv3 = findViewById( R.id.tvGraphingCanonicalEcuation );
        TextView tv4 = findViewById( R.id.tvGraphingCeroEcuation );

        GraphView graph = findViewById( R.id.graph );

        //Recibe objeto
        Intent recieveGraphing = this.getIntent();
        data= recieveGraphing.getParcelableExtra("data");


        tv1.setText( String.format( "Ec. ordinaria: y= %s x+ %s ",
                convertToFormat(data.getM()), convertToFormat(data.getY0()) ) );
        tv2.setText( String.format( "Ec. general: %s x+ %s y+ %s =0",
                convertToFormat(data.getA()), convertToFormat(data.getB()), convertToFormat(data.getB()) ) );
        tv3.setText( String.format( "Ec. canonica: x/%s + y/%s = 1 ",
                convertToFormat(data.getX0()), convertToFormat(data.getY0()) ) );
        tv4.setText( String.format( "Abscisa en 0: %s + Ordenada en 0: %s",
                convertToFormat(data.getX0()), convertToFormat(data.getY0()) ) );

         PointsCalculate(); // calcula los puntos segun recta

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>( points );
        graph.addSeries( series );

        // Configura los ejes modo dinamico
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);

        PointsGraphSeries<DataPoint> series21 = new PointsGraphSeries<>( points ); //soporte para mostrar puntos

        graph.addSeries( series21 );
        series21.setColor(Color.RED);
        series21.setSize(10);
        series21.setOnDataPointTapListener( (series2, dataPoint) -> Toast.makeText(LinearFGraphingActivity.this,
                "El punto es: "+dataPoint, Toast.LENGTH_SHORT).show() );

    }
/*
*   Segun graph view los puntos deber ordenar en forma descendente con respecto a x (depende del cuadrante)
*
*  Si la recta es una X= constante se fija un rango para el grafico= +/- INTERVAL_Y
*  Si la recta es una X= constante se fija un rango para el grafico= +/- INTERVAL_X**
*  Si la recta es Y=mx se toma como (X1,Y2)=(0,0) y se grafica hasta INTERVAL_X
* Si no es ninguno de los caso anterior se grafica en el cuadrante correspiendo entre (0,X0) y (Y0,0)
 */


    private void PointsCalculate(){

        if (data.isConstXFlag()){ //X= constante

            points =  new DataPoint[]{

                    new DataPoint( data.getX0(),-INTERVAL_Y),
                    new DataPoint( data.getX0(),INTERVAL_Y )
            };

        }else if (data.isConstYFlag()){ //X= constante

            points =  new DataPoint[]{

                    new DataPoint( -INTERVAL_X,data.getY0()),
                    new DataPoint( INTERVAL_X ,data.getY0())
            };

        }else if (data.getX0()<0){//segundo y tercer cuadrante

            points =  new DataPoint[]{

                    new DataPoint( data.getX0(), 0 ),
                    new DataPoint( 0,data.getY0() )
            };
        }else  if (data.getX0()>0){//primer y cuarto cuadrante

            points =  new DataPoint[]{

                    new DataPoint( 0, data.getY0()),
                    new DataPoint( data.getX0(),0 )
            };
        }else  if ( (data.getX0()==0) &&  (data.getY0()==0) ){ //Y=mx

            points = new DataPoint[]{
                    new DataPoint( 0, 0),
                    new DataPoint( INTERVAL_X,INTERVAL_X*data.getM() )
            };

        }

    }

// Vuelve al comienzo
    public void Back(View view ){

        Intent back = new Intent(view.getContext(), MainActivity.class);
        startActivity( back );
    }

    //controla el boton back
    @Override
    public void onBackPressed() {

    }
}




