package com.example.jfmm_10.concentrationgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class juga2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juga2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void game2(View view) {
        Intent i = new Intent(this, gameForOne.class);
        EditText player1 = (EditText) findViewById(R.id.editText2);
        EditText player2 = (EditText) findViewById(R.id.editText3);
        String jugador1 = String.valueOf(player1.getText());
        String jugador2 = String.valueOf(player2.getText());

        if (jugador1.equals("") || jugador1.equals(null))
            i.putExtra("jugador1", "Kakaroto");
        else
            i.putExtra("jugador1", jugador1);

        if (jugador2.equals("") || jugador2.equals(null))
            i.putExtra("jugador2", "Vegeta");
        else
            i.putExtra("jugador2", jugador2);

        i.putExtra("jugadores",2);
        startActivity(i);
    }
}
