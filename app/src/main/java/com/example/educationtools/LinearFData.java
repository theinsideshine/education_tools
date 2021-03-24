package com.example.educationtools;

import android.os.Parcel;
import android.os.Parcelable;

public class LinearFData implements Parcelable {
    private double x = 5;         //x de la recta.
    private double m = 0;         //Pendiente  de la recta.
    private double A,B,C;         //Constantes de la ecuacion general de la recta  Ax+BY+C=0.
    private double X0,Y0;         //Constantes de la ecuacion canonica de la recta. x/X0 + y/Y0 = 1



    public void lfu_gph_calculate_from_ecuation_ordinary(double value_X1,
                                                        double value_Y1,
                                                        double value_X2,
                                                        double value_Y2 ){
        //Ecuacion ordinaria y=mx+Y0.
        m = (value_Y2 - value_Y1) / (value_X2 - value_X1);
        Y0= value_Y1 - ( m * value_X1);

        //Ecuacion general Ax+BY+C=0.  Se despejan de y=mx+Y0
        A = -m;
        B = -1;
        C = -Y0;

        //Ecuacion canonica x/X0 + y/Y0 = 1 Se despejan de y=mx+Y0.
        X0 = -(Y0/m);

    }

    public double getX() {
        return x;
    }

    public double getM() {
        return m;
    }


    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public double getX0() {
        return X0;
    }

    public double getY0() {
        return Y0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble( this.x );
        dest.writeDouble( this.m );
        dest.writeDouble( this.A );
        dest.writeDouble( this.B );
        dest.writeDouble( this.C );
        dest.writeDouble( this.X0 );
        dest.writeDouble( this.Y0 );
    }

    public void readFromParcel(Parcel source) {
        this.x = source.readDouble();
        this.m = source.readDouble();
        this.A = source.readDouble();
        this.B = source.readDouble();
        this.C = source.readDouble();
        this.X0 = source.readDouble();
        this.Y0 = source.readDouble();
    }

    public LinearFData() {
    }

    protected LinearFData(Parcel in) {
        this.x = in.readDouble();
        this.m = in.readDouble();
        this.A = in.readDouble();
        this.B = in.readDouble();
        this.C = in.readDouble();
        this.X0 = in.readDouble();
        this.Y0 = in.readDouble();
    }

    public static final Parcelable.Creator<LinearFData> CREATOR = new Parcelable.Creator<LinearFData>() {
        @Override
        public LinearFData createFromParcel(Parcel source) {
            return new LinearFData( source );
        }

        @Override
        public LinearFData[] newArray(int size) {
            return new LinearFData[size];
        }
    };
}
