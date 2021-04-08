package com.example.educationtools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.TextView;

import com.example.navigatioview2.R;
import com.google.android.material.navigation.NavigationView;

/*
Application in development.
  TODO: Remove the warning that suggests using FragmentContaneirView instead of Fragment
        Write the missing functions of the different types of data inputs.
        Check indentation.
        Review the comments.
        Try on different cell phones.
        Generate the apk and upload it to the google store.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //The event is given to the selection of the menu icon.
        final DrawerLayout drawerLayout = findViewById( R.id.drawerLayout );

        findViewById( R.id.imageMenu ).setOnClickListener( v -> drawerLayout.openDrawer( GravityCompat.START ) );

        //Show inside icons.
        NavigationView navigationView = findViewById( R.id.navigationview );
        navigationView.setItemIconTintList( null );

        //Control to call fragments.
        NavController navController = Navigation.findNavController(  this,R.id.navHostFragment );
        NavigationUI.setupWithNavController( navigationView, navController );

        // Visible the default menu option (Information).
        final TextView textTitle = findViewById( R.id.textTitle );

        navController.addOnDestinationChangedListener( (controller, destination, arguments) -> textTitle.setText( destination.getLabel() ) );

    }//Oncreated

    //Control the back button.
    @Override
    public void onBackPressed() {

    }
}