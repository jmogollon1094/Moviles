using Newtonsoft.Json;
using ProChat.Datos;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net;
using System.Text;
using System.IO;
using System.Threading.Tasks;





namespace ProChat.REST
{
    public static class GetContactoREST
    {

        public static List<User> GetContacto(int ID)
        {
            HttpClient request = new HttpClient();
            string direccion = "http://192.168.250.83:8191/";
            request.BaseAddress = new Uri(direccion);
            request.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage result;
            result = request.GetAsync("rest/contacts/" + ID.ToString()).Result;
            return result.Content.ReadAsAsync<IEnumerable<User>>().Result.ToList();           
        }
    }
}
