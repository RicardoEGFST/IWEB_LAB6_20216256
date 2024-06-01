package org.example.lab6_20216256.daos;
import org.example.lab6_20216256.beans.Peliculas;
import java.sql.*;
import java.util.ArrayList;

public class Dao_pelicula {

    //Método para listar las películas
    public ArrayList<Peliculas> listarPelis() {
        ArrayList<Peliculas> listaPelis = new ArrayList<>();

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
        String query = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre as genero "
                + "FROM Pelicula p JOIN Genero g ON p.idGenero = g.idGenero "
                + "ORDER BY p.rating DESC, p.boxOffice DESC";

        try (Connection conn = DriverManager.getConnection(url, username, password);

             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            //Rellenamos la lista con las películas mientras .next no se quede sin filas y retorne false
            while (rs.next()) {
                Peliculas pelicula = new Peliculas();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTituloPeli(rs.getString("titulo"));
                pelicula.setDirectorPeli(rs.getString("director"));
                pelicula.setAnioPeli(rs.getInt("anoPublicacion"));
                pelicula.setRatingPeli(rs.getDouble("rating"));
                pelicula.setBoxOfficePeli(rs.getDouble("boxOffice"));
                pelicula.setGeneroPeli(rs.getString("genero"));
                listaPelis.add(pelicula);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPelis;
    }

    //Método para obtener una película por su título
    public ArrayList<Peliculas> obtenerPelisTitulo(String titulo) {

        ArrayList<Peliculas> listaPelis = new ArrayList<>();
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
        String query = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre as genero "
                + "FROM Pelicula p JOIN Genero g ON p.idGenero = g.idGenero "
                + "WHERE p.titulo LIKE ? ORDER BY p.rating DESC, p.boxOffice DESC";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + titulo + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Peliculas pelicula = new Peliculas();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTituloPeli(rs.getString("titulo"));
                pelicula.setDirectorPeli(rs.getString("director"));
                pelicula.setAnioPeli(rs.getInt("anoPublicacion"));
                pelicula.setRatingPeli(rs.getDouble("rating"));
                pelicula.setBoxOfficePeli(rs.getDouble("boxOffice"));
                pelicula.setGeneroPeli(rs.getString("genero"));
                listaPelis.add(pelicula);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPelis;
    }

    public Peliculas obtenerIdPeli(int id) {

        Peliculas pelicula = new Peliculas();
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
        String query = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre as genero "
                + "FROM Pelicula p JOIN Genero g ON p.idGenero = g.idGenero "
                + "WHERE p.idPelicula = ?";
        //Usamos PrepareStatement pues el id de la película se ingresa
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            //Establecemos el valor del id y ejecutamos
            ResultSet rs = pstmt.executeQuery();
            //Rellenamos la lista con los actores mientras .next no se quede sin filas y retorne false
            while (rs.next()) {
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTituloPeli(rs.getString("titulo"));
                pelicula.setDirectorPeli(rs.getString("director"));
                pelicula.setAnioPeli(rs.getInt("anoPublicacion"));
                pelicula.setRatingPeli(rs.getDouble("rating"));
                pelicula.setBoxOfficePeli(rs.getDouble("boxOffice"));
                pelicula.setGeneroPeli(rs.getString("genero"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Retornamos la lista
        return pelicula;
    }
}






