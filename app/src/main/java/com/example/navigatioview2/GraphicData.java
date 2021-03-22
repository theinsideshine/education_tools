package com.example.navigatioview2;

public class GraphicData {

    private double x=5, b=1, m=1 ;


    public double getB(){
        return b;
        }

    public double getM(){
        return m;
    }

    public double getX(){
        return x;
    }

    public void calculateTwoPoint (double X1, double Y1, double X2, double Y2 )
    {
        m = (Y2-Y1) / (X2-X1);
        b = Y1 - (m*X1);
    }
}
