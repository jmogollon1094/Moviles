package REST;

import android.os.AsyncTask;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import Datos.Mensaje;

/**
 * Created by Alejandro on 11/04/2016.
 */
public class PostArchivoREST extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... archivo) {

        RestTemplate restTemplate = new RestTemplate();
        Mensaje men= new Mensaje();

        String url = "http://192.168.250.83:8191/rest/files/{fromUserId}/{toUserId}";

        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        MultiValueMap<String, Object> arch = new LinkedMultiValueMap<String, Object>();

        arch.add("file", new FileSystemResource(archivo[2]));

        String result = restTemplate.postForObject(url, arch, String.class,archivo[0],archivo[1]);
        return null;
    }
}
