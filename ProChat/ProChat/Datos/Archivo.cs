using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.Datos
{
    public class Archivo
    {
        public int id { get; set; }
        public string name { get; set; }
        public string contentType { get; set; }
        public int from { get; set; }
        public int to { get; set; }
        public string date { get; set; }
    }
}
