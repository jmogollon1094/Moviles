package REST;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Datos.Mensaje;

/**
 * Created by Alejandro on 11/04/2016.
 */
public class GetMensajeREST extends AsyncTask<String, Void, ArrayList<ArrayList<String>>> {
    @Override
    protected ArrayList<ArrayList<String>> doInBackground(String... user) {

        RestTemplate restTemplate = new RestTemplate();
        List<Mensaje> myObjects = null;
        ObjectMapper mapper = new ObjectMapper();

        String url = "http://192.168.250.83:8191/rest/messages/{from}/{to}";

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        String resultado = restTemplate.getForObject(url, String.class,user[1],user[0]);

        try {
            myObjects = mapper.readValue(resultado, new TypeReference<List<Mensaje>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> cosas = new ArrayList<ArrayList<String>>();


        for (int i=0; i<myObjects.size();i+=1){
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(String.valueOf(myObjects.get(i).getFrom()));
            tmp.add(String.valueOf(myObjects.get(i).getTo()));
            tmp.add(myObjects.get(i).getText());
            cosas.add(tmp);
        }

        return cosas;
    }
}
