package org.example.lab6iweb.servlets;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab6_20216256.beans.Actores;
import org.example.lab6_20216256.daos.Dao_actor;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Lista de Actores", value = "/Actores")

public class ActorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Dao_actor actoresDao = new Dao_actor();

        int idPelicula = Integer.parseInt(request.getParameter("id"));
        ArrayList<Actores> listaActores = actoresDao.listarActores(idPelicula);
        String nombrePelicula = actoresDao.obtenerNombrePelicula(idPelicula);

        //Objeto que se enviara a la vista
        request.setAttribute("listaactores", listaActores);
        request.setAttribute("nombrePelicula", nombrePelicula);
        RequestDispatcher view = request.getRequestDispatcher("listaActores.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void destroy() {
    }
}
