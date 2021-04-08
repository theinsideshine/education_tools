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

    public static final int INTERVAL_X = 10 ;     //  Interval to graph from the cutoff at X = 0.
    public static final int INTERVAL_Y = 10 ;     //  Interval to graph from the cutoff at Y = 0.

    // Returns the format with two decimal places.
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

        //Receive object.
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

         PointsCalculate(); // Calculate the points of the line.

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>( points );
        graph.addSeries( series );

        // Set the dynamic mode axes.
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);

        PointsGraphSeries<DataPoint> series21 = new PointsGraphSeries<>( points ); //Soporte para mostrar puntos.

        graph.addSeries( series21 );
        series21.setColor(Color.RED);
        series21.setSize(10);
        series21.setOnDataPointTapListener( (series2, dataPoint) -> Toast.makeText(LinearFGraphingActivity.this,
                "El punto es: "+dataPoint, Toast.LENGTH_SHORT).show() );

    }
/*
 * According to graph view, the points must be ordered in descending order with respect to x (depends on the quadrant)
 *
 * If the line is an X = constant, a range is set for the graph = +/- INTERVAL_Y
 * If the line is an X = constant, a range is set for the graph = +/- INTERVAL_X **
 * If the line is Y = mx it is taken as (X1, Y2) = (0,0) and it is plotted up to INTERVAL_X
 * If it is not any of the previous case, it is plotted in the quadrant corresponding between (0, X0) and (Y0,0)
 */


    private void PointsCalculate(){

        if (data.isConstXFlag()){ //X = constant.

            points =  new DataPoint[]{

                    new DataPoint( data.getX0(),-INTERVAL_Y),
                    new DataPoint( data.getX0(),INTERVAL_Y )
            };

        }else if (data.isConstYFlag()){ //Y = constant.

            points =  new DataPoint[]{

                    new DataPoint( -INTERVAL_X,data.getY0()),
                    new DataPoint( INTERVAL_X ,data.getY0())
            };

        }else if (data.getX0()<0){//Second and third quadrant.

            points =  new DataPoint[]{

                    new DataPoint( data.getX0(), 0 ),
                    new DataPoint( 0,data.getY0() )
            };
        }else  if (data.getX0()>0){//First and fourth quadrants.

            points =  new DataPoint[]{

                    new DataPoint( 0, data.getY0()),
                    new DataPoint( data.getX0(),0 )
            };
        }else  if ( (data.getX0()==0) &&  (data.getY0()==0) ){ // Y  =m x

            points = new DataPoint[]{
                    new DataPoint( 0, 0),
                    new DataPoint( INTERVAL_X,INTERVAL_X*data.getM() )
            };

        }

    }

// Return to start.
    public void Back(View view ){

        Intent back = new Intent(view.getContext(), MainActivity.class);
        startActivity( back );
    }

    //Control the back button.
    @Override
    public void onBackPressed() {

    }
}




