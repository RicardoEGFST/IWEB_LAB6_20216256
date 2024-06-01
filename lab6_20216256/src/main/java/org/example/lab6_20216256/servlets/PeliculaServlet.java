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

@WebServlet(name = "Peliculas", value = "/Pelis")
public class PeliculaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Dao_pelicula peliculaDao = new Dao_pelicula();
        ArrayList<Peliculas> listaPeliculas = peliculaDao.listarPelis();
        //Busqueda
        String searchQuery = request.getParameter("search");

        //Cuando no se realizan busquedas, se muestran todas las peliculas
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            listaPeliculas = peliculaDao.obtenerPelisTitulo(searchQuery);
        } else {
            listaPeliculas = peliculaDao.listarPelis();
        }
        //Objeto que se enviara a la vista
        request.setAttribute("listapelis",listaPeliculas);
        RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    public void destroy() {
    }
}
