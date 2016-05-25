using ProChat.Datos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.Model
{
    class Mensajes
    {
        public List<Mensaje> Conversacion { get; set; }

        public Mensajes(int idFrom, int idTo,string persona)
        {
            DB.DB Base = new DB.DB();
            Conversacion = Base.getAllData(idFrom, idTo, persona);
        }

    }
}
