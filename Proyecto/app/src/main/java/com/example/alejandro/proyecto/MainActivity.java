package com.example.alejandro.proyecto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import REST.GetContactoREST;

public class MainActivity extends ListActivity {
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> cosas = new ArrayList<String>();
        GetContactoREST u =new GetContactoREST();
        String user = getIntent().getStringExtra("user");
        ID = Integer.valueOf(user);
        try {
            cosas =  u.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.milayout, cosas);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void abrirChat(View view) {
        Intent i = new Intent(this, OpenContact.class);
        String name= String.valueOf(((Button)view).getText());
        i.putExtra("name",name);
        i.putExtra("ID",ID);

        startActivity(i);
    }
}
