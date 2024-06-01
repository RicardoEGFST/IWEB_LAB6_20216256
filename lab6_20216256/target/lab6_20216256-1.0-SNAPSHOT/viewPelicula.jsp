<%--
  Created by IntelliJ IDEA.
  User: Ricardo
  Date: 31/05/2024
  Time: 07:36 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.lab6_20216256.beans.Peliculas" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%
    Peliculas pelicula = (Peliculas) request.getAttribute("peliculamostrar");
    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
    String boxOfficeFormatted = formatter.format(pelicula.getBoxOfficePeli());
%>
<!DOCTYPE html>
<html>
<head>
    <title><%=pelicula.getTituloPeli()%></title>
</head>
<body>
<h1><%=pelicula.getTituloPeli()%></h1>
<table border="1">
    <tr>
        <th>idPelicula</th>
        <td><%=pelicula.getIdPelicula()%></td>
    </tr>
    <tr>
        <th>Director</th>
        <td><%=pelicula.getDirectorPeli()%></td>
    </tr>
    <tr>
        <th>Año</th>
        <td><%=pelicula.getAnioPeli()%></td>
    </tr>
    <tr>
        <th>Rating</th>
        <td><%=pelicula.getRatingPeli()%>/10</td>
    </tr>
    <tr>
        <th>Box Office</th>
        <td>$<%=boxOfficeFormatted%></td>
    </tr>
    <tr>
        <th>Género</th>
        <td><%=pelicula.getGeneroPeli()%></td>
    </tr>
    <tr>
        <th>Actores</th>
        <td><a href="ListaActores?id=<%=pelicula.getIdPelicula()%>">Ver Actores</a></td>
    </tr>
</table>

</body>
</html>