using RestSharp;
using RestSharp.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.REST
{
    static class GetArchivoREST
    {
        public static void GetArchivo(int IdFile, string NameFile)
        {
            var client = new RestClient("http://192.168.250.83:8191");
            var request = new RestRequest("rest/files/" + IdFile, Method.GET);
            client.DownloadData(request).SaveAs("C:/Users/Jfmm-10/Documents/Universidad/9 Semestre/Moviles/ProChat/" + NameFile);

        }

    }
}
