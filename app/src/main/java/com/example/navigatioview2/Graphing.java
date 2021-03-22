package com.example.navigatioview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graphing extends AppCompatActivity {


    LineGraphSeries<DataPoint> series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_graphing );

        GraphView graph = (GraphView) findViewById( R.id.graph );

        GraphicData data = new GraphicData();

        data.calculateTwoPoint( 0,0,1,1 );

        double valueX = data.getX();  // variable x comienzo de impresion
        double valueB = data.getB();   // ordenada en el origen
        double valueM = data.getM();   //pendiente
        double valueY =0 ;             // variable y



        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 500; i++){
            valueX = valueX +0.1;
            valueY= valueM * valueX + valueB;
            series.appendData(new DataPoint( valueX, valueY ),true,500);
        }
        graph.addSeries( series );
    }
}
