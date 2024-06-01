
//Importamos las librerías a emplear
package org.example.lab6_20216256.daos;
import org.example.lab6_20216256.beans.Actores;
import java.sql.*;
import java.util.ArrayList;

public class Dao_actor {

    //Primer método, listar actores, que nos permite obtener los actores de la base de datos
    public ArrayList<Actores> listarActores(int idPeli) {
        //Creamos el arreglo en el que se almacenarán los actores
        ArrayList<Actores> lista = new ArrayList<>();
        //Esta excepción se usa para asegurarnos que podamos conectarse a una base
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Establecemos los parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "123";

        // Definimos el query mediante el cual obtendremos los datos que necesitamos de la base de datos
        String query = "SELECT a.idActor, a.nombre, a.apellido, a.anoNacimiento, a.premioOscar " +
                "FROM Actor a " +
                "JOIN Protagonistas p ON a.idActor = p.idActor " +
                "WHERE p.idPelicula = ?";
        //Usamos PrepareStatement pues el id de la película se ingresa
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            //Establecemos el valor del id de la película
            pstmt.setInt(1, idPeli);
            ResultSet rs = pstmt.executeQuery();

            //Rellenamos la lista con los actores mientras .next no se quede sin filas y retorne false
            while (rs.next()) {
                Actores actor = new Actores();
                actor.setIdActor(rs.getInt("idActor"));
                actor.setNombre(rs.getString("nombre"));
                actor.setApellido(rs.getString("apellido"));
                actor.setAnioNacimiento(rs.getInt("anoNacimiento"));
                actor.setGanador(rs.getBoolean("premioOscar"));
                lista.add(actor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Retornamos la lista con los actores
        return lista;
    }

    public String obtenerNombrePelicula(int id) {
        //Definimos e inicializamos la variable nombre en la que se pondrá el nombre de la película
        String nombre = ".";

        //Verficamos que se pueda establecer conexión con una base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Establecemos los parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "123";

        // Definimos el query con el que se obtiene el nombre de la película
        String query = "SELECT titulo FROM Pelicula WHERE idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            //Solo si encontramos establecemos el valor
            if (rs.next()) {
                nombre = rs.getString("titulo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Retornamos el nombre
        return nombre;
    }
}