<%-- 
    Document   : verPostulanteListar
    Created on : Aug 10, 2018, 10:48:20 AM
    Author     : Gisel
--%>

<%@page import="Classes.ManejadorHistorialBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial - Ficha</title>
    </head>
    <body>
        <%
            ManejadorHistorialBD ms = new ManejadorHistorialBD();
                int ci= Integer.valueOf(request.getParameter("ci"));
                int anio= Integer.valueOf(request.getParameter("anio"));
                HttpSession sesion= request.getSession();
               out.print( ms.imprimirFichaPostulante(ci,Integer.parseInt(sesion.getAttribute("usuarioID").toString()),anio));
        %>
    </body>
</html>
