using ProChat.Datos;
using ProChat.REST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ProChat
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            
            /*List<User> lista = GetContactoREST.GetContacto(2);
            foreach (User m in lista)
            {
                Console.WriteLine(m.Nombre);
            }
            List<Mensaje> mensajes = GetMensajeREST.GetMensaje(1, 2);
            foreach (Mensaje m in mensajes)
            {
                Console.WriteLine(m.Text);
            }*/
            InitializeComponent();
            PostMensajeREST.SendMessage(1,2,"TOMALOOOOOO");


        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            Button usuario = (Button)sender;
            string user = usuario.Content.ToString();
            int id = (int)Char.GetNumericValue(user[0]);
            Contactos chat = new Contactos(id);
            chat.Show();
            this.Close();
                        
        }
    }
}
