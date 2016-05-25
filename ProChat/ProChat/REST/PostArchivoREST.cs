using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.REST
{
    static public class PostArchivoREST
    {

        static public void Upload(string filename, int idfrom, int idto)
        {
            var client = new RestClient("http://192.168.250.83:8191");
            var request = new RestRequest("rest/files/" + idfrom.ToString() + "/" + idto.ToString(), Method.POST);
            request.AddFile("file", filename);
            var result = client.ExecuteAsync(request, (response) =>
            {
                Console.WriteLine("Bien");
            });

            
        }
    }
}
