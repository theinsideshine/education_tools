package com.example.educationtools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigatioview2.R;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nav_information#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nav_information extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nav_information() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nav_information.
     */
    // TODO: Rename and change types and number of parameters
    public static nav_information newInstance(String param1, String param2) {
        nav_information fragment = new nav_information();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }


    private TextView tv2_2,tv3_2, tv4_2, tv5_2;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate( R.layout.fragment_nav_information, container, false );

        tv2_2 = (TextView) mView.findViewById( R.id.tv_inf_2_2 );
        tv3_2 = (TextView) mView.findViewById( R.id.tv_inf_3_2 );
        tv4_2 = (TextView) mView.findViewById( R.id.tv_inf_4_2 );
        tv5_2 = (TextView) mView.findViewById( R.id.tv_inf_5_2 );

        tv2_2.setOnClickListener( this );
        tv3_2.setOnClickListener( this );
        tv4_2.setOnClickListener( this );
        tv5_2.setOnClickListener( this );

        return mView;

    }

    @Override
    public void onClick(View v) {
        String url = "http://google.com.ar/"; //Por defecto

        switch (v.getId()) {
            case R.id.tv_inf_2_2:
                url = "http://www.fb.me/theinsideshine/";
                break;

            case R.id.tv_inf_3_2:
                url = "http://www.instagram.com/educacion.ta";
                break;
            case R.id.tv_inf_4_2:
                url = "http://www.github.com/theinsideshine/EducacionTools";
                break;
            case R.id.tv_inf_5_2:
                url = "http://www.youtube.com/channel/UClLTMbxqK8LLSWm4bOdyx5Q";
                break;
        }
        //abre Url
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData( Uri.parse(url));
        startActivity(i);
    }
}