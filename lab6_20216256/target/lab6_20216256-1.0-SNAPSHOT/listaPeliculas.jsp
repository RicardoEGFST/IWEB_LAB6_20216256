<%--
  Created by IntelliJ IDEA.
  User: Ricardo
  Date: 31/05/2024
  Time: 06:51 p. m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.lab6_20216256.beans.Peliculas" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%
    ArrayList<Peliculas> listaPeliculas = (ArrayList<Peliculas>) request.getAttribute("listapelis");
%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Lista de películas</title>
        </head>
        <body>
            <h1>Lista de películas</h1>

            <form method="get" action="Pelis">
                <input type="text" name="search" placeholder="Buscar por título">
                <input type="submit" value="Buscar">
            </form>

            <%
                if (listaPeliculas == null || listaPeliculas.isEmpty()) {
            %>
            <p>No se encontraron películas;</p>
            <%
            } else {
            %>
            <table border="1">
                <thead>
                <tr>
                    <th>Título</th>
                    <th>Director</th>
                    <th>Año de Publicación</th>
                    <th>Rating</th>
                    <th>Box Office</th>
                    <th>Género</th>
                    <th>Actores</th>
                </tr>
                </thead>
                <tbody>
                <%
                    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
                    for (Peliculas pelicula : listaPeliculas) {
                        String boxOfficeFormatted = formatter.format(pelicula.getBoxOfficePeli());
                %>
                        <tr>
                            <td><a href="Detalles?id=<%=pelicula.getIdPelicula()%>"><%=pelicula.getTituloPeli()%></a></td>
                            <td><%=pelicula.getDirectorPeli()%></td>
                            <td><%=pelicula.getAnioPeli()%></td>
                            <td><%=pelicula.getRatingPeli()%>/10</td>
                            <td>$<%=boxOfficeFormatted%></td>
                            <td><%=pelicula.getGeneroPeli()%></td>
                            <td><a href="Actores?id=<%=pelicula.getIdPelicula()%>">Ver Actores</a></td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
        </body>
    </html>
