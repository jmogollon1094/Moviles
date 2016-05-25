using ProChat.Datos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.Model
{
    class Archivos
    {
        public List<Archivo> Docs { get; set; }

        public Archivos(int idFrom,int idTo)
        {
            Docs = REST.GetCompartidoREST.GetCompartido(idFrom, idTo);
        }
    }
}
