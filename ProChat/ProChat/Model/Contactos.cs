using ProChat.Datos;
using ProChat.REST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.Model
{
    class Contactos
    {
        public List<User> Contacts { get; set; }

        public Contactos(int id)
        {
            Contacts = GetContactoREST.GetContacto(id);
        }
    }
}
