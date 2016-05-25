using ProChat.Datos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.REST
{
    static class GetMensajeREST
    {
        public static List<Mensaje> GetMensaje(int IdFrom, int IdTo)
        {
            HttpClient request = new HttpClient();
            string direccion = "http://192.168.250.83:8191/";
            request.BaseAddress = new Uri(direccion);
            request.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage result;
            result = request.GetAsync("rest/messages/" + IdFrom.ToString()+"/"+IdTo.ToString()).Result;
            return result.Content.ReadAsAsync<IEnumerable<Mensaje>>().Result.ToList();
        }
    }
}
