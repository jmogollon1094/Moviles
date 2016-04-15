package com.example.alejandro.proyecto;

import android.app.DownloadManager;
import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Datos.DatabaseHelper;
import REST.GetArchivoREST;
import REST.GetCompartidoREST;
import REST.GetMensajeREST;
import REST.PostMensajeREST;

public class OpenContact extends ListActivity {
    TabHost th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_contact);

        th = (TabHost)findViewById(R.id.tabHost);
        th.setup();
        TabSpec ts1 = th.newTabSpec("Tab1");
        ts1.setIndicator("Chat");
        ts1.setContent(R.id.tab1);

        th.addTab(ts1);

        th.setup();
        TabSpec ts2 = th.newTabSpec("Tab2");
        ts2.setIndicator("Archivos");
        ts2.setContent(R.id.tab2);

        th.addTab(ts2);

        String nombre = getIntent().getStringExtra("name");
        Button name = (Button) findViewById(R.id.name);
        Button name2 = (Button) findViewById(R.id.name2);
        name.setText(nombre);
        name2.setText(nombre);

    }

    public void cargarMensaje(View view) {
        int idA = getIntent().getIntExtra("ID", 1);
        String nome = String.valueOf(((Button) findViewById(R.id.name)).getText());
        String[] dats = nome.split("\\s+");
        int idB= Integer.valueOf(dats[0]);

        DatabaseHelper myDb = new DatabaseHelper(this);

        GetMensajeREST rst = new GetMensajeREST();
        ArrayList<ArrayList<String>> salida=null;
        try {
            salida=rst.execute(String.valueOf(idA),String.valueOf(idB)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i=0; i<salida.size();i+=1){
            myDb.insertData(idB, idA, salida.get(i).get(2));
        }

        GetArchivoREST archivo =new GetArchivoREST();



        Cursor res=myDb.getAllData(idA,idB);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            if (idA==res.getInt(1)){
                buffer.append("Yo: " + res.getString(3) + "\n");
            }
            else {
                buffer.append(dats[1]+ ": " + res.getString(3) + "\n");
            }
        }
        showMessage("Data", buffer.toString());




        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.milayout, historial);
        //setListAdapter(adapter);

    }

    public void enviar(View view) {
        DatabaseHelper myDb = new DatabaseHelper(this);

        EditText ms = (EditText) findViewById(R.id.et);
        String msg = String.valueOf(ms.getText());
        int idA = getIntent().getIntExtra("ID", 1);
        String nome = String.valueOf(((Button) findViewById(R.id.name)).getText());
        String[] dats = nome.split("\\s+");
        int idB= Integer.valueOf(dats[0]);

        boolean isInserted = myDb.insertData(idA, idB, msg);

        PostMensajeREST rst=new PostMensajeREST();
        rst.execute(String.valueOf(idA),String.valueOf(idB),msg);

        if(isInserted =true)
            Toast.makeText(OpenContact.this, "Mensaje enviado", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(OpenContact.this,"Error al enviar el mensaje",Toast.LENGTH_LONG).show();

        ms.setText("");

    }

    public void showMessage(String title,String Message){
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();*/
        TextView mostrar = (TextView) findViewById(R.id.textView);
        mostrar.setMovementMethod(new ScrollingMovementMethod());
        mostrar.setText(Message);
    }

    public void DescargarArchivos(View v) {
        Button boton = (Button)v;
        String dat = (String) boton.getText();

        String[] dats = dat.split("\\s+");
        String id=dats[0];
        String name = (dats[1]);


        DownloadManager download;
        download = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://192.168.250.83:8191/rest/files/"+id);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(name);
        request.setDescription("Android Data download using DownloadManager.");
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name);
        download.enqueue(request);
    }

    public void cargarArchivos(View view) {
        int idA = getIntent().getIntExtra("ID", 1);
        String nome = String.valueOf(((Button) findViewById(R.id.name)).getText());
        String[] dats = nome.split("\\s+");
        int idB= Integer.valueOf(dats[0]);

        GetCompartidoREST rst =new GetCompartidoREST();

        ArrayList<ArrayList<String>> salida=null;
        try {
            salida=rst.execute(String.valueOf(idA),String.valueOf(idB)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayList<String> cosas = new ArrayList<String>();

        for (int i=0; i<salida.size();i+=1){
            cosas.add(salida.get(i).get(0)+" "+salida.get(i).get(4));
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.milay2, cosas);
        setListAdapter(adapter);

    }
}
