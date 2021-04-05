package com.example.educationtools;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigatioview2.R;

public class nav_linear_function extends Fragment {

    public nav_linear_function() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_nav_linear_function, container, false );

        Button btnOpen =  view.findViewById( R.id.btnLfuTwoPoint );
        btnOpen.setOnClickListener( v -> {
            Intent in = new Intent(getActivity(), LinearFTwoPointActivity.class);
            startActivity( in );
        } );
        return view;
    }
}