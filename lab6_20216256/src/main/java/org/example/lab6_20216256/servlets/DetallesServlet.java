package org.example.lab6_20216256.servlets;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab6_20216256.beans.Peliculas;
import org.example.lab6_20216256.daos.Dao_pelicula;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Detalles", value = "/Detalles")
public class DetallesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //Creamos el objeto para la clase Dao
        Dao_pelicula dao_pelicula = new Dao_pelicula();
        //Obtenemos el id
        int idPelicula = Integer.parseInt(request.getParameter("id"));
        Peliculas peliculas = dao_pelicula.obtenerIdPeli(idPelicula);

        //Establecemos el objeto que se enviara a la vista
        request.setAttribute("peliculamostrar",peliculas);
        RequestDispatcher view = request.getRequestDispatcher("viewPelicula.jsp");
        view.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {
    }
}