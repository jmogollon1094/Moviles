using Microsoft.Win32;
using ProChat.Datos;
using ProChat.REST;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace ProChat
{
    /// <summary>
    /// Interaction logic for Chat.xaml
    /// </summary>
    public partial class Chat : Window
    {
        private int idFrom;
        private int idTo;
        private Dictionary<string, int> ids = new Dictionary<string, int>();

        public Chat(string user,int idFrom,int idTo)
        {
            
            InitializeComponent();
            persona.Content = user;
            persona2.Content = user;
            this.idFrom = idFrom;
            this.idTo = idTo;
            

        }

        private void Send(object sender, RoutedEventArgs e)
        {
            string msj = mensaje.Text;
            mensaje.Clear();
            PostMensajeREST.SendMessage(idFrom, idTo, msj);
            DB.DB Base = new DB.DB();
            Base.insertData(msj, idFrom, idTo);
        }

        private void Actualizar(object sender, RoutedEventArgs e)
        {
            List<Mensaje> msj = GetMensajeREST.GetMensaje(idTo, idFrom);
            DB.DB Base = new DB.DB();
            foreach (Mensaje m in msj)
            {
                Base.insertData(m.text, m.from, m.to);
            }
            string otro = persona.Content.ToString();
            Model.Mensajes Mes = new Model.Mensajes(idFrom, idTo,otro);
            DataContext = Mes;

            
        }

        private void Act_Archivos(object sender, RoutedEventArgs e)
        {
            Model.Archivos Files = new Model.Archivos(idFrom, idTo);
            DataContext = Files;
            foreach (Archivo a in Files.Docs)
            {
                try
                {
                    ids.Add(a.name, a.id);
                }
                catch (Exception)
                {
                }
                
            }

        
            
        }

        private void Up_Archivo(object sender, RoutedEventArgs e)
        {
            var ofd = new OpenFileDialog();
            var result = ofd.ShowDialog();
            PostArchivoREST.Upload(ofd.FileName,idFrom,idTo);
            
        }

        private void Download(object sender, RoutedEventArgs e)
        {
            Button hola = (Button)sender;
            string name = hola.Content.ToString();
            int id = ids[name];
            GetArchivoREST.GetArchivo(id, name);
        }
    }
}
