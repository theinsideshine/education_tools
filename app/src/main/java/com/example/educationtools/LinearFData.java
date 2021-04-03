package com.example.educationtools;

import android.os.Parcel;
import android.os.Parcelable;


public class LinearFData implements Parcelable {
    private double x = 5;                    //x de la recta.
    private double m = 0;                     //Pendiente  de la recta.
    private double A=0,B=0,C=0;                    //Constantes de la ecuacion general de la recta  Ax+BY+C=0.
    private double X0=0,Y0=0;                    //Constantes de la ecuacion canonica de la recta. x/X0 + y/Y0 = 1
    private boolean ConstXFlag = false;      //Flag para grafico de X = cte.
    private boolean ConstYFlag = false;      //Flag para grafico de Y = cte.


    //Constructor
    public LinearFData() {
    }

    //Codigos de error.
    private final static boolean OP_OK = true;
    private final static boolean OP_ERROR = false;

    protected LinearFData(Parcel in) {
        x = in.readDouble();
        m = in.readDouble();
        A = in.readDouble();
        B = in.readDouble();
        C = in.readDouble();
        X0 = in.readDouble();
        Y0 = in.readDouble();
        ConstXFlag = in.readByte() != 0;
        ConstYFlag = in.readByte() != 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble( x );
        dest.writeDouble( m );
        dest.writeDouble( A );
        dest.writeDouble( B );
        dest.writeDouble( C );
        dest.writeDouble( X0 );
        dest.writeDouble( Y0 );
        dest.writeByte( (byte) (ConstXFlag ? 1 : 0) );
        dest.writeByte( (byte) (ConstYFlag ? 1 : 0) );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LinearFData> CREATOR = new Creator<LinearFData>() {
        @Override
        public LinearFData createFromParcel(Parcel in) {
            return new LinearFData( in );
        }

        @Override
        public LinearFData[] newArray(int size) {
            return new LinearFData[size];
        }
    };

    public boolean lfu_gph_calculate_from_ecuation_ordinary(double value_X1,
                                                          double value_Y1,
                                                          double value_X2,
                                                          double value_Y2 ){



        // Condicion indefinida

        if( (value_Y2 == value_Y1) &&  (value_X2 == value_X1) ) {
            return OP_ERROR;
        }

        if (value_Y2 == value_Y1) {  // y=Y0
            ConstYFlag  = true;
            m= 0 ;
            Y0 = value_Y1;
            X0 = 0 ;
            A = -m;
            B = -1;
            C = -Y0;


        }else if (value_X2 == value_X1){  //X=X0
                    ConstXFlag  = true;
                    m = 1 ;
                    X0 = value_X2;
                    Y0 = 0;
                    A = m;
                    B = 0 ;
                    C = -X0;

              }   else {
                        m = (value_Y2 - value_Y1) / (value_X2 - value_X1);
                        Y0= value_Y1 - ( m * value_X1);
                        //Ecuacion general Ax+BY+C=0.  Se despejan de y=mx+Y0
                        A = -m;
                        B = -1;
                        C = -Y0;

                        //Ecuacion canonica x/X0 + y/Y0 = 1 Se despejan de y=mx+Y0.
                        try{
                            X0 = -(Y0/m);
                        }catch (ArithmeticException ex){

                            return OP_ERROR;
                        }
        }
        return OP_OK;
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

    public boolean isConstXFlag() {
        return ConstXFlag;
    }

    public boolean isConstYFlag() {
        return ConstYFlag;
    }
}
