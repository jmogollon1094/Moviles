package com.example.alejandro.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void cargarContactos(View view) {
        Intent i= new Intent(this,MainActivity.class);
        switch (view.getId()) {
            case R.id.b1:
                i.putExtra("user", "1");
                break;
            case R.id.b2:
                i.putExtra("user", "2");
                break;
            case R.id.b3:
                i.putExtra("user", "3");
                break;
            case R.id.b4:
                i.putExtra("user", "4");
                break;
            case R.id.b5:
                i.putExtra("user", "5");
                break;
        }

        startActivity(i);
    }
}
