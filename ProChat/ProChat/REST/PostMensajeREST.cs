using ProChat.Datos;
using RestSharp;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.REST
{
    static class PostMensajeREST
    {
        public static async void SendMessage(int From, int To, string Texto)
        {
            var client = new HttpClient();
            client.BaseAddress = new Uri("http://192.168.250.83:8191/");
            HttpResponseMessage response;
            var json = new Mensaje() { from = From, to = To, text = Texto };
            response = await client.PostAsJsonAsync("rest/messages", json);
            if (response.IsSuccessStatusCode)
            {
                // Get the URI of the created resource.
                Uri gizmoUrl = response.Headers.Location;
            }
        }
    }
}
