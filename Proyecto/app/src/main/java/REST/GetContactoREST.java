package REST;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Datos.User;

/**
 * Created by Alejandro on 10/04/2016.
 */
public class GetContactoREST  extends AsyncTask<String, Void, ArrayList<String>> {
    @Override
    protected ArrayList<String> doInBackground(String... user) {

        RestTemplate restTemplate = new RestTemplate();
        List<User> myObjects = null;
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<String> cosas = new ArrayList<String>();

        String url = "http://192.168.250.83:8191/rest/contacts/{idUser}";

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        String resultado = restTemplate.getForObject(url, String.class,user[0]);



        try {
            myObjects = mapper.readValue(resultado, new TypeReference<List<User>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0; i<myObjects.size();i+=1){
            cosas.add(myObjects.get(i).getUserId()+" "+myObjects.get(i).getUserName());
        }

        return cosas;
    }
}
