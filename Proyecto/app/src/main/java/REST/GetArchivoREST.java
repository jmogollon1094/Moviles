package REST;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alejandro on 11/04/2016.
 */
public class GetArchivoREST extends AppCompatActivity {


    public Void descargar(String id,String name) {

        DownloadManager download;
        download = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://192.168.250.83:8191/rest/files/" + id);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(name);
        request.setDescription("Android Data download using DownloadManager.");
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name);
        download.enqueue(request);

        return null;
    }

}
