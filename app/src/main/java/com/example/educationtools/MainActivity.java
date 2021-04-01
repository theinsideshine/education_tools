package com.example.educationtools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.navigatioview2.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Se le da el evento a la seleccion del icono de menu
        final DrawerLayout drawerLayout = findViewById( R.id.drawerLayout );

        findViewById( R.id.imageMenu ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer( GravityCompat.START );
            }
        } );

        //Muestra el interior de los iconos
        NavigationView navigationView = findViewById( R.id.navigationview );
        navigationView.setItemIconTintList( null );

        //Control para llamar a los fragmentos
        NavController navController = Navigation.findNavController(  this,R.id.navHostFragment );
        NavigationUI.setupWithNavController( navigationView, navController );

        // pone visible la opcion del menu por defecto(Profile)
        final TextView textTitle = findViewById( R.id.textTitle );

        navController.addOnDestinationChangedListener( new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText( destination.getLabel() );
            }
        } );

    }//Oncreated

    //controla el boton back
    @Override
    public void onBackPressed() {

    }
}