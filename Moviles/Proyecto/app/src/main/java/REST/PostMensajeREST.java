package REST;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import Datos.Mensaje;

/**
 * Created by Alejandro on 11/04/2016.
 */
public class PostMensajeREST extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... mensaje) {

        RestTemplate restTemplate = new RestTemplate();
        Mensaje men= new Mensaje();

        men.setFrom(Integer.parseInt(mensaje[0]));
        men.setTo(Integer.parseInt(mensaje[1]));
        men.setText(mensaje[2]);

        String url = "http://192.168.250.83:8191/rest/messages/";

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        restTemplate.postForObject(url, men, Mensaje.class);
        return null;
    }
}
