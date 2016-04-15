package REST;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Datos.Archivo;

/**
 * Created by Alejandro on 11/04/2016.
 */
public class GetCompartidoREST extends AsyncTask<String, Void, ArrayList<ArrayList<String>>> {
    protected ArrayList<ArrayList<String>> doInBackground(String... user) {

        RestTemplate restTemplate = new RestTemplate();
        List<Archivo> myObjects = null;
        ObjectMapper mapper = new ObjectMapper();


        String url = "http://192.168.250.83:8191/rest/shared_files/{from}/{to}";

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        String resultado = restTemplate.getForObject(url, String.class,user[0],user[1]);



        try {
            myObjects = mapper.readValue(resultado, new TypeReference<List<Archivo>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> cosas = new ArrayList<ArrayList<String>>();


        for (int i=0; i<myObjects.size();i+=1){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(String.valueOf(myObjects.get(i).getId()));
            tmp.add(String.valueOf(myObjects.get(i).getFrom()));
            tmp.add(String.valueOf(myObjects.get(i).getTo()));
            tmp.add(myObjects.get(i).getContentType());
            tmp.add(myObjects.get(i).getName());
            cosas.add(tmp);
        }

        return cosas;
    }
}
