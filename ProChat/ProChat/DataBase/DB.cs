using ProChat.Datos;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProChat.DB
{
    public class DB
    {
        private SQLiteConnection conn = new SQLiteConnection("Data Source=ProDB.db");

        public DB()
        {
            conn.Open();
            var command = conn.CreateCommand();
            command.CommandText = "CREATE TABLE IF NOT EXISTS Mensaje(ID INTEGER PRIMARY KEY AUTOINCREMENT, DE INTEGER, PARA INTEGER, MENSAJE TEXT)";
            command.ExecuteNonQuery();
            conn.Close();
        }

        public void insertData(String Mensaje, int De, int Para)
        {
            String sentencia = "INSERT INTO Mensaje(DE,PARA,MENSAJE) values (" + De.ToString() + ", " + Para.ToString() + ", " + "'" + Mensaje + "')";
            conn.Open();
            var command = conn.CreateCommand();
            command.CommandText = sentencia;
            command.ExecuteNonQuery();
            conn.Close();

        }

        public List<Mensaje> getAllData(int De, int Para, string persona)
        {
            String sentencioa = "SELECT * FROM Mensaje WHERE (DE=" + De.ToString() + " AND PARA=" + Para.ToString() + ") or (DE=" + Para.ToString() + " and PARA=" + De.ToString() + ")";
            conn.Open();
            var command = conn.CreateCommand();
            command.CommandText = sentencioa;
            SQLiteDataReader sdr = command.ExecuteReader();
            List<Mensaje> Conv = new List<Mensaje>();
            while (sdr.Read())
            {
                Mensaje p;
                if (sdr.GetInt32(1)==De)
                {
                    p = new Mensaje(sdr.GetInt32(1), sdr.GetInt32(2),"Yo: "+sdr.GetString(3));
                }
                else
                {
                    p = new Mensaje(sdr.GetInt32(1), sdr.GetInt32(2),persona+": "+sdr.GetString(3));
                }
                
                Conv.Add(p);
            }
            return Conv;

        }
    }
}
