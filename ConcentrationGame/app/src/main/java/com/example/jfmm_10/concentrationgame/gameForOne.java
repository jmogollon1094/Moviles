package com.example.jfmm_10.concentrationgame;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.Identity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class gameForOne extends AppCompatActivity {

    List<Integer> valores = new ArrayList<Integer>();
    List<String> players;
    int temporal;
    Button p1_button;
    Button p2_button;
    int but1;
    int but2;
    int score1;
    int score;
    int score2;
    String Nombre;
    int jugadores;
    int jug;
    int destapadas;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        destapadas=0;
        score = 0;
        score1=0;
        score2=0;
        temporal = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_for_one);
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

        Nombre = getIntent().getStringExtra("nombre");

        jugadores=getIntent().getIntExtra("jugadores", 1);

        jug=0;

        if (jugadores==1) {
            TextView t = new TextView(this);
            t = (TextView) findViewById(R.id.textView3);
            t.setText(getString(R.string.primer) + Nombre);

            TextView s = new TextView(this);
            s = (TextView) findViewById(R.id.textView5);
            s.setText(getString(R.string.puntaje) + String.valueOf(score1));
        }


        else if (jugadores==2){
            players=new ArrayList<>(2);
            players.add(getIntent().getStringExtra("jugador1"));
            players.add(getIntent().getStringExtra("jugador2"));

            TextView t = (TextView) findViewById(R.id.textView3);
            t.setText(getString(R.string.primer) + players.get(jug));

            TextView s = new TextView(this);
            s = (TextView) findViewById(R.id.textView5);
            s.setText(getString(R.string.puntaje) + String.valueOf(score));
        }

        generateGame2();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void generateGame2() {
        Random random = new Random();
        for (int i = 0; i < 8; i = i + 1) {
            int numero = random.nextInt(100);
            valores.add(i, numero);
        }

        for (int j = 8; j < 16; j = j + 1) {
            valores.add(j, valores.get(j - 8));
        }
    }

    public void generateGame() {
        for (int i = 0; i < 16; i = i + 1) {
            valores.add(i, 0);
        }
        int i = 0;
        int signal = 0;
        while (i < 16) {
            Random random = new Random();
            int n = random.nextInt(100 - 1) + 1;

            while (signal < 1) {
                if (valores.get(i) == 0) {
                    valores.add(i, n);
                    signal = 1;
                }
            }

            int k = random.nextInt(16 - i) + i;
            signal = 0;
            while (signal < 1) {
                if (valores.get(i + k) == 0) {
                    valores.add(k, n);
                    signal = 1;
                }
            }
            i = i + 1;
        }
    }


    public void Open(View view) {
        if (temporal == 0) {
            p1_button = (Button) findViewById(R.id.button5);
            p1_button.setText(String.valueOf(valores.get(0)));
            temporal = valores.get(0);
        } else {
            p2_button = (Button) findViewById(R.id.button5);
            p2_button.setText(String.valueOf(valores.get(0)));
            if (temporal == valores.get(0)) {

                Context context = getApplicationContext();
                CharSequence text = "Bien hecho: " + players.get(jug);
                int duration = Toast.LENGTH_SHORT;
                if (jug==0)
                    score = score + 5;
                else if (jug==1)
                    score2=score2 + 5;
                score1+=1;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                temporal = 0;
            } else {
                temporal = 0;
                p2_button = (Button) findViewById(R.id.button5);
                p2_button.setText("");
            }
        }
    }

    public void Open2(View v) throws InterruptedException {
        switch (v.getId()) {
            case R.id.button5:
                //En caso que se presione el primer botón
                if (temporal == 0) {
                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button5);
                    p1_button.setText(String.valueOf(valores.get(0)));
                    p1_button.setEnabled(false);
                    temporal = valores.get(0);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button5);
                    p2_button.setText(String.valueOf(valores.get(0)));
                    if (temporal == valores.get(0)) {

                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        esperar2(but1, but2);
                        p1_button.setEnabled(false);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button5);
                        p2_button.setText(String.valueOf(valores.get(0)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(0)));

                    }
                    jug=jug+1;
                }
                break;
            case R.id.button6:
                // do something else
                if (temporal == 0) {
                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button6);
                    p1_button.setText(String.valueOf(valores.get(1)));
                    temporal = valores.get(1);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button6);
                    p2_button.setText(String.valueOf(valores.get(1)));
                    if (temporal == valores.get(1)) {

                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button6);
                        p2_button.setText(String.valueOf(valores.get(1)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(1)));
                    }
                    jug=jug+1;
                }
                break;
            case R.id.button7:
                // i'm lazy, do nothing
                if (temporal == 0) {

                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button7);
                    p1_button.setText(String.valueOf(valores.get(2)));
                    temporal = valores.get(2);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button7);
                    p2_button.setText(String.valueOf(valores.get(2)));
                    if (temporal == valores.get(2)) {

                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button7);
                        p2_button.setText(String.valueOf(valores.get(2)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(2)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button8:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button8);
                    p1_button.setText(String.valueOf(valores.get(3)));
                    temporal = valores.get(3);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button8);
                    p2_button.setText(String.valueOf(valores.get(3)));
                    if (temporal == valores.get(3)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button8);
                        p2_button.setText(String.valueOf(valores.get(3)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(3)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button9:
                // i'm lazy, do nothing
                if (temporal == 0) {

                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button9);
                    p1_button.setText(String.valueOf(valores.get(4)));
                    temporal = valores.get(4);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button9);
                    p2_button.setText(String.valueOf(valores.get(4)));
                    if (temporal == valores.get(4)) {

                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button9);
                        p2_button.setText(String.valueOf(valores.get(4)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(4)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button10:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button10);
                    p1_button.setText(String.valueOf(valores.get(5)));
                    temporal = valores.get(5);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button10);
                    p2_button.setText(String.valueOf(valores.get(5)));
                    if (temporal == valores.get(5)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button10);
                        p2_button.setText(String.valueOf(valores.get(5)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(5)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button11:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button11);
                    p1_button.setText(String.valueOf(valores.get(6)));
                    temporal = valores.get(6);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button11);
                    p2_button.setText(String.valueOf(valores.get(6)));
                    if (temporal == valores.get(6)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button11);
                        p2_button.setText(String.valueOf(valores.get(6)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(6)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button12:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button12);
                    p1_button.setText(String.valueOf(valores.get(7)));
                    temporal = valores.get(7);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button12);
                    p2_button.setText(String.valueOf(valores.get(7)));
                    if (temporal == valores.get(7)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button12);
                        p2_button.setText(String.valueOf(valores.get(7)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(7)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button13:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button13);
                    p1_button.setText(String.valueOf(valores.get(8)));
                    temporal = valores.get(8);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button13);
                    p2_button.setText(String.valueOf(valores.get(8)));
                    if (temporal == valores.get(8)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button13);
                        p2_button.setText(String.valueOf(valores.get(8)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(8)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button14:
                // i'm lazy, do nothing
                if (temporal == 0) {

                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button14);
                    p1_button.setText(String.valueOf(valores.get(9)));
                    temporal = valores.get(9);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button14);
                    p2_button.setText(String.valueOf(valores.get(9)));
                    if (temporal == valores.get(9)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button14);
                        p2_button.setText(String.valueOf(valores.get(9)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(9)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button15:
                // i'm lazy, do nothing
                if (temporal == 0) {

                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button15);
                    p1_button.setText(String.valueOf(valores.get(10)));
                    temporal = valores.get(10);
                    p1_button.setEnabled(false);

                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button15);
                    p2_button.setText(String.valueOf(valores.get(10)));
                    if (temporal == valores.get(10)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button15);
                        p2_button.setText(String.valueOf(valores.get(10)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(10)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button16:
                // i'm lazy, do nothing
                if (temporal == 0) {

                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button16);
                    p1_button.setText(String.valueOf(valores.get(11)));
                    temporal = valores.get(11);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button16);
                    p2_button.setText(String.valueOf(valores.get(11)));
                    if (temporal == valores.get(11)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button16);
                        p2_button.setText(String.valueOf(valores.get(11)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(11)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button17:
                // i'm lazy, do nothing
                if (temporal == 0) {

                    but1 = v.getId();
                    p1_button = (Button) findViewById(R.id.button17);
                    p1_button.setText(String.valueOf(valores.get(12)));
                    temporal = valores.get(12);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button17);
                    p2_button.setText(String.valueOf(valores.get(12)));
                    if (temporal == valores.get(12)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        destapadas=destapadas+1;
                        score1=score1+5;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button17);
                        p2_button.setText(String.valueOf(valores.get(12)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(12)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button18:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button18);
                    p1_button.setText(String.valueOf(valores.get(13)));
                    temporal = valores.get(13);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button18);
                    p2_button.setText(String.valueOf(valores.get(13)));
                    if (temporal == valores.get(13)) {
                        if (jug==1)
                            score = score + 5;
                        else if (jug==2)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button18);
                        p2_button.setText(String.valueOf(valores.get(13)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(13)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button19:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button19);
                    p1_button.setText(String.valueOf(valores.get(14)));
                    p1_button.setEnabled(false);
                    temporal = valores.get(14);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button19);
                    p2_button.setText(String.valueOf(valores.get(14)));
                    if (temporal == valores.get(14)) {
                        if (jug==0)
                            score = score + 5;
                        else if (jug==1)
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button19);
                        p2_button.setText(String.valueOf(valores.get(14)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(14)));
                    }
                    jug=jug+1;
                }

                break;

            case R.id.button20:
                // i'm lazy, do nothing
                if (temporal == 0) {
                    but1 = v.getId();

                    p1_button = (Button) findViewById(R.id.button20);
                    p1_button.setText(String.valueOf(valores.get(15)));
                    temporal = valores.get(15);
                    p1_button.setEnabled(false);
                } else {

                    but2 = v.getId();
                    p2_button = (Button) findViewById(R.id.button20);
                    p2_button.setText(String.valueOf(valores.get(15)));
                    if (temporal == valores.get(15)) {
                        if (jug==2)
                            score = score + 5;
                        else
                            score2=score2 + 5;
                        score1=score1+5;
                        destapadas=destapadas+1;
                        Context context = getApplicationContext();
                        CharSequence text = getString(R.string.bien)+ " + 5";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        temporal = 0;
                        p2_button.setEnabled(false);
                        p1_button = (Button) findViewById(but1);
                        p1_button.setEnabled(false);
                        esperar2(but1, but2);
                    } else {

                        temporal = 0;
                        p2_button = (Button) findViewById(R.id.button20);
                        p2_button.setText(String.valueOf(valores.get(15)));
                        p1_button = (Button) findViewById(but1);
                        esperar(but1, but2);
                        p2_button.setText(String.valueOf(valores.get(15)));
                    }
                    jug=jug+1;
                }

                break;



        }

        if (jugadores==2 && jug>1)
            jug=0;


        if(jugadores==1) {
            TextView s = new TextView(this);
            s = (TextView) findViewById(R.id.textView5);
            s.setText(getString(R.string.puntaje) + String.valueOf(score1));
        }


        if (jugadores==2 && destapadas>7){
            Intent i = new Intent(this, Pop.class);
            int puntaje=0;
            if (score>score2) {
                Nombre = players.get(0);
                puntaje=score;
            }
            if (score2>score) {
                Nombre = players.get(1);
                puntaje=score2;
            }
            if (score==score2) {
                Nombre = "Esto es un Empate";
                puntaje=score;
            }
            i.putExtra("nombre", Nombre);
            i.putExtra("score",puntaje);
            i.putExtra("jugadores",2);
            startActivity(i);
        }

        if (jugadores==1 && score1==40) {
            Intent i = new Intent(this, Pop.class);
            i.putExtra("nombre", Nombre);
            i.putExtra("score",score1);
            i.putExtra("jugadores",1);
            startActivity(i);
        }
    }

    private void esperar(final int but1, final int but2) {
        final Handler handler = new Handler();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        p1_button = (Button) findViewById(but1);
                        p2_button = (Button) findViewById(but2);

                        p1_button.setText("");
                        p2_button.setText("");

                        if (jugadores==2) {
                            TextView t = (TextView) findViewById(R.id.textView3);
                            t.setText(getString(R.string.primer) + players.get(jug));
                            if (jug==0) {
                                TextView s=(TextView) findViewById(R.id.textView5);
                                s.setText(getString(R.string.puntaje) + String.valueOf(score));
                            }

                            if (jug==1) {
                                TextView s = (TextView) findViewById(R.id.textView5);
                                s.setText(getString(R.string.puntaje) + String.valueOf(score2));
                            }
                        }


                        p1_button.setEnabled(true);
                        p2_button.setEnabled(true);


                    }
                });
            }
        }, 1000);
    }


    private void esperar2(final int but1, final int but2) {
        final Handler handler = new Handler();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        p1_button = (Button) findViewById(but1);
                        p2_button = (Button) findViewById(but2);

                        if (jugadores==2) {
                            TextView t = (TextView) findViewById(R.id.textView3);
                            t.setText("Jugador: " + players.get(jug));

                            if (jug == 0) {
                                TextView s = (TextView) findViewById(R.id.textView5);
                                s.setText("Puntaje 1: " + String.valueOf(score));
                            }

                            if (jug == 1) {
                                TextView s = (TextView) findViewById(R.id.textView5);
                                s.setText("Puntaje 2: " + String.valueOf(score2));
                            }
                        }

                     }
                });
            }
        }, 1000);
    }


    public void restartGame(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "gameForOne Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.jfmm_10.concentrationgame/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "gameForOne Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.jfmm_10.concentrationgame/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
