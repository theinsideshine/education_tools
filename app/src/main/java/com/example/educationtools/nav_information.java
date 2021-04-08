package com.example.educationtools;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigatioview2.R;

public class nav_information extends Fragment implements View.OnClickListener {

    public nav_information() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    private TextView tv2_2,tv3_2, tv4_2, tv5_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate( R.layout.fragment_nav_information, container, false );

        tv2_2 =  mView.findViewById( R.id.tv_inf_2_2 );
        tv3_2 =  mView.findViewById( R.id.tv_inf_3_2 );
        tv4_2 =  mView.findViewById( R.id.tv_inf_4_2 );
        tv5_2 =  mView.findViewById( R.id.tv_inf_5_2 );

        tv2_2.setOnClickListener( this );
        tv3_2.setOnClickListener( this );
        tv4_2.setOnClickListener( this );
        tv5_2.setOnClickListener( this );

        return mView;

    }
/*
* Don't use switch to stay in line with google's suggestion.
* http://tools.android.com/tips/non-constant-fields*
 */
    @Override
    public void onClick(View v) {

        String url = "http://google.com.ar/"; //Default

      if ( v.getId()== R.id.tv_inf_2_2) {

          url = "http://www.fb.me/theinsideshine/";
          tv2_2.setTextColor( Color.BLUE);

      }else if ( v.getId()== R.id.tv_inf_3_2) {

          url = "http://www.instagram.com/educacion.ta";
          tv3_2.setTextColor( Color.BLUE);
      }else if ( v.getId()== R.id.tv_inf_4_2) {

            url = "http://www.instagram.com/educacion.ta";
            tv4_2.setTextColor( Color.BLUE);

        }else if ( v.getId()== R.id.tv_inf_5_2) {
          url = "http://www.youtube.com/channel/UClLTMbxqK8LLSWm4bOdyx5Q";
          tv5_2.setTextColor( Color.BLUE);

      }

        //Open Url.
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData( Uri.parse(url));        
        startActivity(i);
    }
}