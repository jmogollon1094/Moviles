using ProChat.Datos;
using System;
using System.Collections.Generic;
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
    /// Interaction logic for Contactos.xaml
    /// </summary>
    public partial class Contactos : Window
    {
        
        private int idFrom;
        private Dictionary<string,int> ids= new Dictionary<string, int>();
        
        public Contactos(int id)
        {
            InitializeComponent();
            
            Model.Contactos sopa = new Model.Contactos(id);
            DataContext = sopa;
            foreach (User u in sopa.Contacts)
            {
                ids.Add(u.UserName, u.userId);
            }
            idFrom = id;
            
        }

        private void OpenChat(object sender, RoutedEventArgs e)
        {
            
            Button usuario = (Button)sender;
            string user = usuario.Content.ToString();
            int idTo;
            try
            {
                idTo = ids[user];
            }
            catch (Exception)
            {

                idTo = 0;
            }
            Console.WriteLine(idTo.ToString());
            Chat chat = new Chat(user,idFrom,idTo);
            chat.Show();

        }
    }
}
