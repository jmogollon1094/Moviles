package com.example.jfmm_10.concentrationgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class OnePlayer extends AppCompatActivity {

    EditText PlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
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

        int idioma = getIntent().getIntExtra("idioma",0);
        if (idioma == 1){
            TextView t = (TextView) findViewById(R.id.textView2);
            t.setText("Escribe tu nombre");
            Button b = (Button)findViewById(R.id.button);
            b.setText("Jugar");
        }

    }

    public void playOne(View view) {
        Intent i = new Intent(this, gameForOne.class);
        EditText mEdit   = (EditText)findViewById(R.id.editText);
        String name1 = String.valueOf(mEdit.getText());
        if (name1.equals("") || name1.equals(null))
            i.putExtra("nombre", "Kakaroto");
        else
            i.putExtra("nombre", name1);
        i.putExtra("jugadores",1);
        startActivity(i);
    }
}
