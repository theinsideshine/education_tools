package com.example.educationtools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.navigatioview2.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class
LinearFGraphingActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series;
    private TextView tv1,tv2,tv3,tv4;
    private double x,y;

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

        Intent recieveGraphing = this.getIntent();
        data= recieveGraphing.getParcelableExtra("data");
        x = data.getX(); //Coordenada x para empezar a graficar


        tv1.setText("Ec. ordinaria: y= "+ data.getM() +" x+ "+  data.getY0() );
        tv2.setText("Ec. general: "+ data.getA() +"  x+ "+ data.getB()+" y+" + data.getC() +" =0 ");
        tv3.setText("Ec. canonica: x/"+ data.getX0() +" +y/" + data.getY0()+ " = 1 ");
        tv4.setText("Abcisa en 0: " + data.getX0() +". Ordenada en 0: " + data.getY0());


        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 500; i++){
            x = x +0.1;
            y= data.getM() * x + data.getX0();
            series.appendData(new DataPoint( x, y ),true,500);
        }
        graph.addSeries( series );
    }

    public void Back (View view){

        Intent back = new Intent(this, MainActivity.class);
        startActivity( back );
    }
}


