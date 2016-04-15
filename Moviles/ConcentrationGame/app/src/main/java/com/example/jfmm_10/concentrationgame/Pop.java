package com.example.jfmm_10.concentrationgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Jfmm-10 on 06/03/2016.
 */
public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop1);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);;

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        String Nombre = getIntent().getStringExtra("nombre");
        int score = getIntent().getIntExtra("score", 0);
        TextView t = (TextView) findViewById(R.id.textView5);
        t.setText(Nombre);
        TextView t2 = (TextView) findViewById(R.id.textView6);
        t2.setText(String.valueOf(score));
    }

    public void rematch(View view) {
        Intent i;
        int jugadores = getIntent().getIntExtra("jugadores",1);
        if (jugadores==2)
            i = new Intent(this, juga2.class);
        else
            i = new Intent(this, OnePlayer.class);
        startActivity(i);
    }

    public void menu(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void cerrar(View view) {
        int p = android.os.Process.myPid();
        android.os.Process.killProcess(p);
    }
}
