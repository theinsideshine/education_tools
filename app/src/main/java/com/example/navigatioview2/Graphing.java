package com.example.navigatioview2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graphing extends AppCompatActivity {

    private double x=5,b,m,y;
    private LineGraphSeries<DataPoint> series;
    private TextView tv1,tv2,tv3,tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_graphing );
        tv1 =findViewById( R.id.textView );
        tv2 =findViewById( R.id.textView2);
        tv3 =findViewById( R.id.textView3);
        tv4 =findViewById( R.id.textView4);

        GraphView graph = (GraphView) findViewById( R.id.graph );

        Intent recieveGraphing = this.getIntent();
        b = recieveGraphing.getDoubleExtra( "b",0 );
        m = recieveGraphing.getDoubleExtra( "m",0 );



        tv1.setText("Ecuacion: y= "+ m +" x+ "+  b );
        tv2.setText("Ecuacion general: "+ (m*-1) +" x+ 1y +" + (b*1)+" =0 ");
        tv3.setText("Ecuacion canonica: y/"+ b +" + x/" + (-b/m)+ " = 1 ");
        tv4.setText("Abcisa en el origen: " + (-b/m) +". Ordenada en el origen: " + b);





        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 500; i++){
            x = x +0.1;
            y= m * x + b;
            series.appendData(new DataPoint( x, y ),true,500);
        }
        graph.addSeries( series );
    }
}


