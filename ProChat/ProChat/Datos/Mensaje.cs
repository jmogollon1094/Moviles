using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.Datos
{
    public class Mensaje
    {
        public int id { get; set; }

        public int from { get; set; }

        public int to { get; set; }

        public string date { get; set; }

        public string text { get; set; }

        public Mensaje(int id1, int id2, string text)
        {
            from = id1;
            to = id2;
            this.text = text;
        }

        public Mensaje()
        {
        }

        
    }
}
